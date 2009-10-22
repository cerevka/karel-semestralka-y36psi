package karel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server {

    public Server(int port) {
        try {
            // vytvoření serverového socketu pro navázání spojení
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                System.out.println("Server ceka na pripojeni klienta");
                Socket clientSocket = serverSocket.accept();
                new ClientThread(clientSocket);
                System.out.println("Pripojil jsem dalsiho klienta.");
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
