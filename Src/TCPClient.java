import java.io.*;
import java.net.Socket;

public class TCPClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 6789;

    public static void main(String[] args) {
        // Attempt to connect to the server
        try (Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
             BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("Client successfully established TCP connection.");
            System.out.println("Local port: " + clientSocket.getLocalPort());
            System.out.println("Connected to server on port: " + clientSocket.getPort());

            // Main loop: read user input, send to server, and print response
            while (true) {
                System.out.print("Enter message (type 'exit' to quit): ");
                String userMessage = userInputReader.readLine();

                if (userMessage == null || userMessage.trim().isEmpty()) {
                    System.out.println("Empty message ignored.");
                    continue;
                }

                if (userMessage.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting client...");
                    break;
                }

                outToServer.writeBytes(userMessage + '\n');  // Send message to server

                // Receive and print the server's response
                String serverResponse = inFromServer.readLine();
                if (serverResponse == null) {
                    System.out.println("Server connection closed.");
                    break;
                }
                
                System.out.println("FROM SERVER: " + serverResponse);
            }

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}