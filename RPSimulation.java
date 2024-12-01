import java.util.*;

public class RPSimulation {

    static class Node {
        int id;
        Map<Integer, Integer> neighbors; // Neighbor -> Cost

        public Node(int id) {
            this.id = id;
            this.neighbors = new HashMap<>();
        }

        public void addNeighbor(int neighbor, int cost) {
            neighbors.put(neighbor, cost);
        }
    }

    static class Network {
        Map<Integer, Node> nodes = new HashMap<>();

        public void addNode(int id) {
            nodes.putIfAbsent(id, new Node(id));
        }

        public void addLink(int src, int dest, int cost) {
            nodes.get(src).addNeighbor(dest, cost);
            nodes.get(dest).addNeighbor(src, cost); // Bidirectional link
        }
    }

    // Distance Vector Routing Simulation
    public static int simulateDVR(Network network) {
        System.out.println("Simulating Distance Vector Routing...");
        int convergenceTime = 0; // Example metric
        // Implement DVR algorithm here
        // For simplicity, simulate convergence in a few iterations
        convergenceTime = network.nodes.size() * 2;
        System.out.println("DVR Convergence Time: " + convergenceTime + "ms");
        return convergenceTime;
    }

    // Link State Routing Simulation
    public static int simulateLSR(Network network) {
        System.out.println("Simulating Link State Routing...");
        int convergenceTime = 0; // Example metric
        // Implement LSR algorithm here
        // For simplicity, simulate convergence in fewer iterations
        convergenceTime = network.nodes.size();
        System.out.println("LSR Convergence Time: " + convergenceTime + "ms");
        return convergenceTime;
    }

    public static void main(String[] args) {
        Network network = new Network();

        // Example Network Setup
        network.addNode(1);
        network.addNode(2);
        network.addNode(3);
        network.addNode(4);

        network.addLink(1, 2, 1);
        network.addLink(1, 3, 4);
        network.addLink(2, 3, 2);
        network.addLink(3, 4, 1);

        System.out.println("Network Created.");

        // Simulate both protocols and compare
        int dvrTime = simulateDVR(network);
        int lsrTime = simulateLSR(network);

        // Evaluate and Compare
        System.out.println("\nPerformance Evaluation:");
        System.out.println("DVR Convergence Time: " + dvrTime + "ms");
        System.out.println("LSR Convergence Time: " + lsrTime + "ms");

        if (dvrTime < lsrTime) {
            System.out.println("DVR is faster for this topology.");
        } else {
            System.out.println("LSR is faster for this topology.");
        }
    }
}