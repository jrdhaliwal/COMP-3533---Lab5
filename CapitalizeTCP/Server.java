package CapitalizeTCP;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String cappedSentence;

        // Create server socket
        ServerSocket welcomeSocket = new ServerSocket(5935);
        System.out.println("Server listening on port " + welcomeSocket.getLocalPort());

        // Create infinite loop to welcome incoming requests
        while (true) {
            // Creates new socket object where we can get input/output stream from client
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Successfully accepted connection from client with following IP and port: "
            + connectionSocket.getInetAddress()
            + " : " 
            + connectionSocket.getPort());
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream toClient = new DataOutputStream(connectionSocket.getOutputStream());

            try {
                while (true) {
                    clientSentence = fromClient.readLine();
                    cappedSentence = clientSentence.toUpperCase() + '\n';
   
                    toClient.writeBytes(cappedSentence);
                }
            } catch (Exception e) {
                System.out.println("Client connection closed.");
            } finally {
                welcomeSocket.close();
            }
        }
    }
}
