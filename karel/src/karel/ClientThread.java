package karel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Trida, ktera mam na starosti obsluhu klienta, pro kazdeho klienta se spousti
 * zvlastni vlakno.
 */
public class ClientThread implements Runnable {

    //==SOUKROMÉ OBJEKTOVÉ PROMĚNNÉ=============================================
    private BufferedReader reader;
    private BufferedWriter writer;
    private Thread thread = null;
    private ControlModul modul;


    //==KONSTRUKTOR=============================================================
    /**
     * Konstruktor vytvari vstupni a vystupni streamy na zaklade predanych
     * udaju v socketu
     * @param socket Obsahuje udaje o klientovi, se kterym se komunikuje
     */
    public ClientThread(Socket socket) {
        try {
            // vytvori vstupni stream
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // vytvori vystupni stream
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // vytvori se ControlModul s aplikacni logikou
            modul = new ControlModul();
            // nastartuje vlakno
            this.start();
        } catch (IOException ex) {
            System.err.println("ClientThread hlasi: IOException");
        }
    }

    /**
     * Metoda, ktera ma starosti vytvoreni vlakna a jeho odstartovani.
     */
    public void start() {
        thread = new Thread();
        thread.run();

    }

    /**
     * Metoda definujici beh vlakna
     */
    public void run() {
    }
}
