import java.io.*;
import java.net.*;

public class chatClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Replace with server's IP if needed
        int serverPort = 12345; // Server's port number

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            System.out.println("Connected to the chat server!");

            // Set up input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Client-Server chat loop
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage, serverMessage;

            while (true) {
                // Send a message to the server
                System.out.print("You: ");
                clientMessage = clientInput.readLine();
                out.println(clientMessage);
                if (clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Chat ended.");
                    break;
                }

                // Receive a message from the server
                serverMessage = in.readLine();
                if (serverMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Server ended the chat.");
                    break;
                }
                System.out.println("Server: " + serverMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
