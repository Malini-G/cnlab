import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class EchoClient1 {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Server address
        int serverPort = 9876; // Server port
        
        try (DatagramSocket clientSocket cs= new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Enter a message to send to the server (type 'exit' to quit):");
            while (true) {
                System.out.print("Your message: ");
                String messageToSend = scanner.nextLine();
                
                if (messageToSend.equalsIgnoreCase("exit")) {
                    break;
                }
                
                // Send message to server
                byte[] sendBuffer = messageToSend.getBytes();
                InetAddress serverInetAddress = InetAddress.getByName(serverAddress);
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverInetAddress, serverPort);
                clientSoc    
				ket.send(sendPacket);
                
                // Receive echo message from server
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                
                // Verify if the received message matches the sent message
                if (receivedMessage.equals(messageToSend)) {
                    System.out.println("Echo verified: " + receivedMessage);
                } else {
                    System.out.println("Mismatch! Sent: " + messageToSend + ", Received: " + receivedMessage);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
