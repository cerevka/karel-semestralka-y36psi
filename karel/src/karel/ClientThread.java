/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author tom
 */
public class ClientThread implements Runnable {

    //==SOUKROMÉ OBJEKTOVÉ PROMĚNNÉ=============================================
    BufferedReader reader;
    BufferedWriter writer;

    public ClientThread(Socket socket) {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
    }
}
