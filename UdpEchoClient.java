import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpEchoClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Server IP
        int serverPort = 12345;             // Server port

        try (DatagramSocket clientSocket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {
            InetAddress serverIP = InetAddress.getByName(serverAddress);

            System.out.println("Enter a message to send to the server (type 'exit' to quit):");

            while (true) {
                System.out.print("You: ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                // Send the message to the server
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, serverPort);
                clientSocket.send(sendPacket);

                // Receive the echoed message from the server
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                String echoedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + echoedMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
