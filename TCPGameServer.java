import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class TCPGameServer {
    private static final int PORT = 5935;
    private static final String FILE = "words.txt";
    private static final ArrayList<String> words = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
       loadFile();

       // Create server socket
       ServerSocket welcomeSocket =  new ServerSocket(PORT);
       System.out.println("Server listening on port " + PORT);
    }

    // Method to load words.txt file into ArrayList
    private static void loadFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
