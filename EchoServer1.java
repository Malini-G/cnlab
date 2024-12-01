import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EchoServer1 {
    public static void main(String[] args) {
        int port = 9876; // Port to listen on
        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("Echo server is running on port " + port);
            byte[] receiveBuffer  = new byte[1024];
            
            while (true) {
                // Receive data from client
                DatagramPacket receivePacket  = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                
                System.out.println("Received: " + receivedMessage);
                
                // Echo the message back to the client
                DatagramPacket sendPacket = new DatagramPacket(
                        receivedMessage.getBytes(),
                        receivedMessage.getBytes().length,
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
