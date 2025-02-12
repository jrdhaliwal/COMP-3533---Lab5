import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPGameClient {
    // Initializing local server address and port
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5935;

    public static void main(String[] args) {
        try {
            // Connect to server
            Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Client successfully connected server at IP:Port: "
            + clientSocket.getInetAddress()
            + ":"
            + clientSocket.getPort());

            // Create input stream to read from user
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            
            // Create output stream
            DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());

            // Create input stream server side
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Send "HELLO" message
            System.out.println("Send a HELLO message to begin: ");
            String hello = inFromUser.readLine();

            toServer.writeBytes(hello);
            toServer.flush();

            // Wait for server response
            String response = fromServer.readLine();
            System.out.println("Received From Server: " + response);

        } catch (Exception e) {
            
        }
    }
}
