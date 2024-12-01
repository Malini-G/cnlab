import java.net.*;
public class DNSServer {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(9876)) { // Port number
            System.out.println("DNS Server is running...");
            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer;

            while (true) {
                // Receive packet from client
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                String domainName = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received request for domain: " + domainName);

                // Mock IP address response (could be replaced with actual DNS logic)
                String ipAddress = "93.184.216.34"; // Example IP for any domain
                sendBuffer = ipAddress.getBytes();

                // Send the response back to client
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
                System.out.println("Sent IP address: " + ipAddress);
            }
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

