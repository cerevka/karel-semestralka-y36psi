package karel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Trida, ktera mam na starosti obsluhu klienta, pro kazdeho klienta se spousti
 * zvlastni vlakno.
 */
public class ClientThread implements Runnable {

    //==SOUKROMÉ OBJEKTOVÉ PROMĚNNÉ=============================================
    private BufferedReader reader;
    private BufferedWriter writer;
    private Socket socket;
    private Thread thread;
    private ControlModul modul;
    String instruction = "";

    //==KONSTRUKTOR=============================================================
    /**
     * Konstruktor vytvari vstupni a vystupni streamy na zaklade predanych
     * udaju v socketu
     * @param socket Obsahuje udaje o klientovi, se kterym se komunikuje
     */
    public ClientThread(Socket socket) {
        try {
            // uchova se pristup k socketu
            this.socket = socket;
            // vytvori vstupni stream
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // vytvori vystupni stream
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // vytvori se ControlModul s aplikacni logikou
            modul = new ControlModul();
            // nastartuje vlakno
        } catch (IOException ex) {
            System.err.println("ClientThread hlasi: IOException");
        }
    }

    //==SOUKROME OBJEKTOVE METODY===============================================
    /**
     * Ukonceni spojeni
     */
    private void closeConnection() {
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException ex) {
            System.err.println("ClientThread hlasi pri uzavirani spojeni: IOException");
        }

    }

    /**
     * Metoda, ktera overuje, zda prichozi prikaz zacina na jmeno robota. Pokud
     * ano, tak je modifikovana hodnota promenne instruction, ze ktere je to
     * jmeno vyjmuto
     * @return true -> osloveni je OK, false -> chybne osloveni
     */
    private boolean controlName() {
        boolean result = false;
        if (instruction.startsWith(modul.getName())) {
            result = true;
            instruction = instruction.substring(modul.getName().length() + 1);
        }
        return result;
    }

    private boolean controlInstruction() {
        boolean result = false;
        // pole akceptovanych prikazu
        String[] acceptedInstruction = {"KROK", "VLEVO", "ZVEDNI", "OPRAVIT 1",
            "OPRAVIT 2", "OPRAVIT 3", "OPRAVIT 4",
            "OPRAVIT 5", "OPRAVIT 6", "OPRAVIT 7",
            "OPRAVIT 8", "OPRAVIT 9"};
        for (int i = 0; i < acceptedInstruction.length; i++) {
            if (instruction.equals(acceptedInstruction[i])) {
                result = true;
                break;
            }
        }
        return result;
    }

    //==VEREJNE OBJEKTOVE METODY================================================
    /**
     * Metoda, ktera ma starosti vytvoreni vlakna a jeho odstartovani.
     */
    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Metoda definujici beh vlakna
     */
    public void run() {
        int currentChar;
        int lastChar = 'x';

        try {
            // poslani uvodniho pozdravu
            writer.write("220 Oslovuj mne " + modul.getName() + ".\r\n");
            writer.flush();

            // zpracovavani instrukci
            while ((currentChar = reader.read()) != -1) {
                // osetreni prilis dlouhych a nesmyslnych prikazu - proste se
                //   prestanou cist
                if (instruction.length() < 40) {
                    instruction += (char) currentChar;
                }
                // pokud byla detekovana koncova sekvence znaku, zacnu prikaz
                //   pracovavat
                if (((char) currentChar) == '\n' && ((char) lastChar) == '\r') {
                    //odstrani se koncova sekvence
                    instruction = instruction.trim();


                    // kontrola, zda je spravne osloveni, pokud ano, odstrani
                    //   ho z instrukce, jinak ceka na dalsi data
                    if (controlName() == false) {
                        writer.write("500 NEZNAMY PRIKAZ\r\n");
                        // pripravi se prostor pro novou instrukci
                        instruction = "";
                        continue;
                    }

                    // kontrola, zda je instrukce validni, jinak ceka dal na
                    //   dalsi data
                    if (controlInstruction() == false) {
                        writer.write("500 NEZNAMY PRIKAZ\r\n");
                        // pripravi se prostor pro novou instrukci
                        instruction = "";
                        continue;
                    }

                    //**********************************************************
                    //   V TUTO CHVILI MAM VALIDNI INSTRUKCI KE ZPRACOVANI
                    //**********************************************************

                    if (instruction.equals("KROK")) {
                        // spusti se metoda pro krok
                        byte result = modul.step();

                        boolean endWhile = false;
                        switch (result) {
                            case 1:
                                writer.write("250 OK "+modul.getCoordinate()+"\r\n");
                                break;
                            case 2:
                                writer.write("530 HAVARIE\r\n");
                                closeConnection();
                                endWhile = true;
                                break;
                            case 3:
                                writer.write("570 PORUCHA BLOK "+modul.getError()+"\r\n");
                                break;
                            case 4:
                                writer.write("572 ROBOT SE ROZPADL\r\n");
                                closeConnection();
                                endWhile = true;
                                break;
                        }

                        // skoncil switch, overim, zda se nema breaknout
                        //   vnejsi cyklus na cteni
                        if (endWhile) {
                            break;
                        }

                    } else {
                        if (instruction.equals("VLEVO")) {
                            // spusti se metoda pro otoceni
                            modul.left();
                        } else {
                            if (instruction.equals("ZVEDNI")) {
                                // spusti se metoda pro zvednuti
                                String secret = modul.secret();
                                if (secret.equals("ERROR")) {
                                    writer.write("550 NELZE ZVEDNOUT ZNACKU\r\n");
                                    // po zavolani teto metody se musi uzavrit spojeni
                                    closeConnection();
                                    break;
                                } else {
                                    writer.write("221 USPECH " + secret+"\r\n");
                                    // po zavolani teto metody se musi uzavrit spojeni
                                    closeConnection();
                                    break;
                                }
                            } else {
                                if (instruction.startsWith("OPRAVIT")) {
                                    // spusti se metoda pro opravu
                                    // je delana se startWith, protoze se jeste
                                    // musi parsovat cislo bloku
                                    int number = (int) (instruction.charAt(instruction.length() - 1) - '0');
                                    if (modul.repair(number)) {
                                        // blok byl opraven
                                        writer.write("250 OK " + modul.getCoordinate()+"\r\n");
                                    } else {
                                        // nebylo co opravit
                                        writer.write("571 NENI PORUCHA\r\n");
                                    }
                                }
                            }

                        }
                    }

                    // pripravi se prostor pro novou instrukci
                    instruction = "";

                }
                lastChar = currentChar;

            }

        } catch (IOException ex) {
            System.err.println("ClientThread hlasi: IOException");
        }


    }
}
