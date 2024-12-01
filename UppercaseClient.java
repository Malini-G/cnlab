import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UppercaseClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Server address
        int port = 12345; // Server port

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to the server.");

            // Get user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a string to send to the server: ");
            String message = scanner.nextLine();

            // Send the message to the server
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(message);

            // Receive the response from the server
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String response = reader.readLine();

            System.out.println("Response from server: " + response);

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}