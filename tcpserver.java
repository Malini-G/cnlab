import java.io.*;
import java.net.*;

public class tcpserver {

    public static void main(String[] args) {
        int port = 12345; // Port number for the server to listen on

        try (ServerSocket sSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            // Wait for a client to connect
            Socket socket = sSocket.accept();
            System.out.println("Client connected.");

            // Read data from the client
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Print the received data
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received: " + message);
            }

            // Close the connection
            socket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
