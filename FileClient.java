import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Server address
        int port = 12345; // Port to connect to

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to the server.");

            // Receive the file from the server
            InputStream inputStream = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File received successfully.");
            bufferedOutputStream.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
