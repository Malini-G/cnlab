import java.net.*;
import java.util.Scanner;

public class DNSClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the domain name to resolve: ");
        String domainName = scanner.nextLine();
        System.out.print("Enter the frame size for the packet: ");
        int frameSize = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (frameSize <= 0) {
            System.out.println("Frame size must be greater than zero.");
            return;
        }

        byte[] receiveBuffer = new byte[frameSize];
        byte[] sendBuffer = domainName.getBytes();

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost"); // Server IP address
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 9876);

            // Send packet to the server
            clientSocket.send(sendPacket);
            System.out.println("Sent domain name: " + domainName);

            // Receive response packet from server
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);

            String ipAddress = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Resolved IP address: " + ipAddress);
        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}