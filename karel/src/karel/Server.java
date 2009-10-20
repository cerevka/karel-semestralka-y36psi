package karel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server {

    //==SOUKROMÉ OBJEKTOVÉ PROMĚNNÉ=============================================
    BufferedReader reader;
    BufferedWriter writer;


    public Server(int port) {
        try {
            // vytvoření serverového socketu pro navázání spojení
            ServerSocket serverSocket = new ServerSocket(port);

            //JOptionPane.showMessageDialog(null, "Server will waiting for connecting a client now (Port: " + port + "). Press OK.", "Starting the server", JOptionPane.INFORMATION_MESSAGE);
            // očekává se připojení pouze jednoho klienta
            Socket clientSocket = serverSocket.accept();

            // vytvoření proudu, ze kterého bude server číst zprávy
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // vytvoření proudu, do kterého bude server zapisovat
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (SocketException e) {
            System.err.println("SocketException");
        } catch (UnknownHostException e) {
            System.err.println("UnknownHost");
        } catch (IOException e) {
            System.err.println("IOException");
        }
    }
}
