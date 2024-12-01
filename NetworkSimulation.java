import java.util.Random;

class Node {
    private String name;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Link {
    private Node node1, node2;

    public Link(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public void transmit(String protocol, int packets) {
        System.out.printf("[%s-%s] Transmitting %d packets using %s\n", 
                node1.getName(), node2.getName(), packets, protocol);
    }
}

class NetworkSimulator {
    private Node n0, n1, n2, n3;
    private Link l0, l1, l2;
    private int tcpPackets = 0;
    private int udpPackets = 0;

    public NetworkSimulator() {
        // Create nodes
        n0 = new Node("n0");
        n1 = new Node("n1");
        n2 = new Node("n2");
        n3 = new Node("n3");

        // Create links
        l0 = new Link(n0, n2);
        l1 = new Link(n1, n2);
        l2 = new Link(n2, n3);
    }

    public void runSimulation(int tcpDataSize, int udpDataSize) {
        System.out.println("\nStarting Simulation...");

        // TCP from n0 to n3 via n2
        System.out.println("TCP Traffic:");
        tcpPackets = sendTCP(tcpDataSize);

        // UDP from n1 to n3 via n2
        System.out.println("\nUDP Traffic:");
        udpPackets = sendUDP(udpDataSize);

        // Display summary
        System.out.println("\nSimulation Complete.");
        System.out.printf("TCP Packets Sent: %d\n", tcpPackets);
        System.out.printf("UDP Packets Sent: %d\n", udpPackets);
    }

    private int sendTCP(int dataSize) {
        Random random = new Random();
        int packets = 0;
        int packetSize = 512; // Bytes per packet
        int totalPackets = (int) Math.ceil((double) dataSize / packetSize);

        for (int i = 1; i <= totalPackets; i++) {
            packets++;
            int delay = random.nextInt(100); // Simulate random delay
            System.out.printf("Packet %d sent. Delay: %dms\n", i, delay);
            l0.transmit("TCP", 1);
            l2.transmit("TCP", 1);
        }
        return packets;
    }

    private int sendUDP(int dataSize) {
        Random random = new Random();
        int packets = 0;
        int packetSize = 1024; // Bytes per packet
        int totalPackets = (int) Math.ceil((double) dataSize / packetSize);

        for (int i = 1; i <= totalPackets; i++) {
            packets++;
            boolean loss = random.nextBoolean(); // Simulate packet loss
            if (loss) {
                System.out.printf("Packet %d lost.\n", i);
                continue;
            }
            System.out.printf("Packet %d sent successfully.\n", i);
            l1.transmit("UDP", 1);
            l2.transmit("UDP", 1);
        }
        return packets;
    }
}

public class NetworkSimulation {
    public static void main(String[] args) {
        // Initialize simulation parameters
        int tcpDataSize = 2048; // TCP data size in bytes
        int udpDataSize = 3072; // UDP data size in bytes

        // Create and run the simulator
        NetworkSimulator simulator = new NetworkSimulator();
        simulator.runSimulation(tcpDataSize, udpDataSize);
    }
}
Starting Simulation...
TCP Traffic:
Packet 1 sent. Delay: 34ms
[n0-n2] Transmitting 1 packets using TCP
[n2-n3] Transmitting 1 packets using TCP
Packet 2 sent. Delay: 27ms
[n0-n2] Transmitting 1 packets using TCP
[n2-n3] Transmitting 1 packets using TCP
Packet 3 sent. Delay: 78ms
[n0-n2] Transmitting 1 packets using TCP
[n2-n3] Transmitting 1 packets using TCP
Packet 4 sent. Delay: 50ms
[n0-n2] Transmitting 1 packets using TCP
[n2-n3] Transmitting 1 packets using TCP

UDP Traffic:
Packet 1 sent successfully.
[n1-n2] Transmitting 1 packets using UDP
[n2-n3] Transmitting 1 packets using UDP
Packet 2 sent successfully.
[n1-n2] Transmitting 1 packets using UDP
[n2-n3] Transmitting 1 packets using UDP
Packet 3 lost.
Packet 4 sent successfully.
[n1-n2] Transmitting 1 packets using UDP
[n2-n3] Transmitting 1 packets using UDP

Simulation Complete.
TCP Packets Sent: 4
UDP Packets Sent: 3

