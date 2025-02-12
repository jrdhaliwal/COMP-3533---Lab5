import java.io.*;
import java.net.*;

public class TCPServer {
    private static final int PORT = 6789;

    public static void main(String[] args) {
        // Create a server socket and wait for client connections
        try (ServerSocket welcomeSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                System.out.println("Waiting for client connection...");
                
                // Accept an incoming client connection
                try (Socket connectionSocket = welcomeSocket.accept();
                     BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                     DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream())) {

                    System.out.println("Client connected from " + connectionSocket.getInetAddress() + ":" + connectionSocket.getPort());

                    // Process client messages
                    String clientSentence;
                    while ((clientSentence = inFromClient.readLine()) != null) {
                        String capitalizedSentence = clientSentence.toUpperCase() + '\n';
                        outToClient.writeBytes(capitalizedSentence);
                    }

                } catch (IOException e) {
                    System.err.println("Error handling client connection: " + e.getMessage());
                }

                System.out.println("Client disconnected.");
            }

        } catch (IOException e) {
            System.err.println("Server failed to start: " + e.getMessage());
        }
    }
}