import java.net.*;
import java.util.HashMap;

public class RARPServer {
    public static void main(String[] args) {
        int port = 9876; // Server port
        DatagramSocket serverSocket = null;
        
        // ARP Table (MAC to IP mapping)
        HashMap<String, String> arpTable = new HashMap<>();
        arpTable.put("00-14-22-01-23-45", "192.168.0.1");
        arpTable.put("00-14-22-01-23-46", "192.168.0.2");
        arpTable.put("00-14-22-01-23-47", "192.168.0.3");

        try {
            serverSocket = new DatagramSocket(port);
            System.out.println("RARP Server is running on port " + port);

            while (true) {
                // Receive a packet from the client
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Extract MAC address from received data
                String macAddress = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received RARP request for MAC address: " + macAddress);

                // Find corresponding IP address from the ARP table
                String ipAddress = arpTable.get(macAddress);
                if (ipAddress != null) {
                    System.out.println("IP address for " + macAddress + ": " + ipAddress);
                    // Send the IP address back to the client
                    DatagramPacket sendPacket = new DatagramPacket(
                            ipAddress.getBytes(), ipAddress.length(),
                            receivePacket.getAddress(), receivePacket.getPort());
                    serverSocket.send(sendPacket);
                } else {
                    String errorMessage = "MAC address not found in the ARP table.";
                    DatagramPacket sendPacket = new DatagramPacket(
                            errorMessage.getBytes(), errorMessage.length(),
                            receivePacket.getAddress(), receivePacket.getPort());
                    serverSocket.send(sendPacket);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        }
    }
}
