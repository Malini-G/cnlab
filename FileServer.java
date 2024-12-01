import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        int port = 12345; // Port number to listen on
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("File Server is running on port " + port);

            // Accept a connection from the client
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Open the file to send
            File file = new File("server_file.txt"); // Replace with the file you want to send
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            // Send file data to the client
            OutputStream outputStream = socket.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully.");
            bufferedInputStream.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
