import java.io.*;
import java.net.*;
public class TCPEchoclient {
 public static void main(String[] args)throws Exception {
 try (Socket socket = new Socket("127.0.0.1", 5678);
 BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
 DataInputStream serverInput = new DataInputStream(socket.getInputStream());
 DataOutputStream serverOutput = new DataOutputStream(socket.getOutputStream())) {
 String message;
 System.out.println("Connected to server. Type messages to send:");
 
 while (true) {
 System.out.print("Enter message: ");
 message = userInput.readLine();
 if (message.equalsIgnoreCase("exit")) {
 System.out.println("Exiting client.");
 break;
 }
 serverOutput.writeBytes(message + "\n");
 String echoedMessage = serverInput.readLine();
 System.out.println("Echoed message: " + echoedMessage);
 }
 } catch (IOException e) {
 System.err.println("Client Error: " + e.getMessage());
 }
 }
}
