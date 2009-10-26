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
        int znak;
        String instruction = "";

        try {            
            writer.write("220 Oslovuj mne "+modul.getName()+".\r\n");
            writer.flush();

            while ((znak = reader.read()) != -1) {
                instruction += (char) znak;
                if (((char) znak) == '\n' && instruction.charAt(instruction.length() - 2) == '\r') {
                    System.out.println("Prijato: " + instruction);
                    instruction = "";
                }
            }



           
            
        } catch (IOException ex) {
            System.err.println("ClientThread hlasi: IOException");
        }


    }
}
