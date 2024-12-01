import java.net.*;
import java.util.Scanner;

public class RARPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Server IP (can be changed to the server's IP)
        int serverPort = 9876; // Server port

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("RARP Client - Enter MAC Address to resolve to IP (e.g., 00-14-22-01-23-45):");
            String macAddress = scanner.nextLine();

            // Send RARP request to the server
            byte[] sendData = macAddress.getBytes();
            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverInetAddress, serverPort);
            clientSocket.send(sendPacket);

            // Receive response from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            // Print the response from the server (IP address or error message)
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server Response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
