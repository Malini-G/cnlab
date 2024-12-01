import java.io.*;
import java.net.*;

public class tcpclient {
    public static void main(String[] args) {
        String hostname = "localhost"; // Server hostname
        int port = 12345; // Port number to connect to

        try (Socket socket = new Socket(hostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            // Send message to the server
            String message = "hello_client";
            writer.println(message);
            System.out.println("Message sent: " + message);
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}