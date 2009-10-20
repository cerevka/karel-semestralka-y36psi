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
                Socket clientSocket = serverSocket.accept();
                new ClientThread(clientSocket);
            }

        } catch (SocketException e) {
            System.err.println("SocketException");
        } catch (UnknownHostException e) {
            System.err.println("UnknownHost");
        } catch (IOException e) {
            System.err.println("IOException");
        }
    }
}
