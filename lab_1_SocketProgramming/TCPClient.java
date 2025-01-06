import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(hostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String text;

            System.out.println("Enter messages (type 'bye' to quit):");
            while ((text = console.readLine()) != null) {
                writer.println(text);
                System.out.println("Server reply: " + reader.readLine());
                
                if ("bye".equalsIgnoreCase(text)) {
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
