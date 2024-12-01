import java.io.*;
import java.net.*;

public class chatServer {
    public static void main(String[] args) {
        int port = 12345; // Server's port number

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chat server started on port " + port);
            System.out.println("Waiting for a client to connect...");

            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            // Set up input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Server-Client chat loop
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage, serverMessage;
            
            while (true) {
                // Receive a message from the client
                clientMessage = in.readLine();
                if (clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Client: " + clientMessage);

                // Send a response to the client
                System.out.print("You: ");
                serverMessage = serverInput.readLine();
                out.println(serverMessage);
                if (serverMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Chat ended.");
                    break;
                }
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
