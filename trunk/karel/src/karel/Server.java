package karel;

/**
 * @version 1.0
 * @author Jan Cermak (cermaja9@fel.cvut.cz) & Tomas Cerevka (cerevtom@fel.cvut.cz)
 * Semestrální práce z Y36PSI - práce číslo 1 - Karel server
 * zadání: https://dsn.felk.cvut.cz/wiki/vyuka/y36psi/cviceni/uloha1-karel-zadani
 * Dokonceno: 28.10.2009
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server {
    //==KONSTRUKTOR=============================================================
    /**
     * Vytvori novou instanci serveru, ktera pripojuje klienty
     * @param port cislo portu, na kterem server bude poslouchat
     */
    public Server(int port) {
        try {
            // vytvoření serverového socketu pro navázání spojení
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server pripraven.");

            while (true) {                
                Socket clientSocket = serverSocket.accept();
                new ClientThread(clientSocket).start(); 
            }

        } catch (SocketException e) {
            System.err.println("Server hlasi: SocketException");
        } catch (UnknownHostException e) {
            System.err.println("Server hlasi: UnknownHost");
        } catch (IOException e) {
            System.err.println("Server hlasi: IOException");
        }
    }
}
