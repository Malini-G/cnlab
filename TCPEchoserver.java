import java.io.*;
import java.net.*;
public class TCPEchoserver {
 
 public static void main(String[] args) throws Exception{
 try (ServerSocket serverSocket = new ServerSocket(5678)) {
 System.out.println("Server started. Waiting for clients...");
 
 try (Socket clientSocket = serverSocket.accept();
 DataInputStream clientInput = new DataInputStream(clientSocket.getInputStream());
 DataOutputStream clientOutput = new 
DataOutputStream(clientSocket.getOutputStream())) {
 System.out.println("Client connected.");
 String message;
 
 while (true) {
 // Read message from client
 message = clientInput.readLine();
 if (message == null || message.equalsIgnoreCase("exit")) {
 System.out.println("Client disconnected.");
 break;
 }
 System.out.println("Received from client: " + message);
 // Echo message back to client
 clientOutput.writeBytes(message + "\n");
 }
 }
 } catch (IOException e) {
 System.err.println("Server Error: " + e.getMessage());
 }
 }
}
