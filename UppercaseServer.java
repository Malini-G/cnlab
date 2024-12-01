import java.io.*;
import java.net.*;

public class UppercaseServer {
    public static void main(String[] args) {
        int port = 12345; // Server listens on this port

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                // Accept client connection
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress());

                // Read client input
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String request = reader.readLine();
                System.out.println("Received request: " + request);

                // Process the request: convert to uppercase
                String response = request.toUpperCase();

                // Send the response back to the client
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(response);
                System.out.println("Sent response: " + response);

                // Close the connection with the client
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}