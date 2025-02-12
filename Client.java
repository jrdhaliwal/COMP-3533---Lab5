import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;

        // Create input stream - read from user
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // Create object of type Socket, connect to server
        Socket clientSocket = new Socket("localhost", 5935);
        System.out.println("Client successfuly connected to server at IP:port: " 
        + clientSocket.getInetAddress() 
        + " : " 
        + clientSocket.getPort());

        // Create output stream
        DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());

        // Create input stream - Initialize input stream side
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Read sentence from the user
        sentence = inFromUser.readLine();

        while (sentence.toLowerCase().compareTo("exit") != 0) {
            // Send sentence to server
            toServer.writeBytes(sentence + '\n');

            // Returned sentence from server
            modifiedSentence = fromServer.readLine();

            // Write out returned sentence
            System.out.println("FROM: " + modifiedSentence);
            sentence = inFromUser.readLine();
        }

        // Close the connection
        clientSocket.close();
    }
}
