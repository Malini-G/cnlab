import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpEchoServer {
    public static void main(String[] args) {
        int port = 12345; // Port to listen on

        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("UDP Echo Server is running on port " + port);

            byte[] buffer = new byte[1024];
            while (true) {
                // Receive packet
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(receivePacket);

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received: " + receivedMessage);

                // Echo back the received packet
                DatagramPacket sendPacket = new DatagramPacket(
                        receivePacket.getData(),
                        receivePacket.getLength(),
                        receivePacket.getAddress(),
                        receivePacket.getPort()
                );
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
