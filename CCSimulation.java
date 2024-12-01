import java.util.Random;

public class CCSimulation {

    // Constants
    private static final int MAX_PACKETS = 100; // Total packets to simulate
    private static final int MAX_WINDOW_SIZE = 50; // Maximum congestion window size
    private static final double LOSS_PROBABILITY = 0.2; // Probability of packet loss (20%)

    public static void main(String[] args) {
        System.out.println("Simulating Congestion Control Algorithms...");
        System.out.println("----------------------------------------");

        // Simulate TCP Tahoe
        System.out.println("Simulation for TCP Tahoe:");
        simulateTCPTahoe();

        System.out.println("\nSimulation for TCP Reno:");
        simulateTCPReno();
    }

    // TCP Tahoe Simulation
    private static void simulateTCPTahoe() {
        int cwnd = 1; // Congestion Window Size (initial)
        int packetsSent = 0;
        Random random = new Random();

        while (packetsSent < MAX_PACKETS) {
            System.out.println("CWND: " + cwnd);

            // Send packets in the current congestion window
            for (int i = 0; i < cwnd && packetsSent < MAX_PACKETS; i++) {
                packetsSent++;
                if (random.nextDouble() < LOSS_PROBABILITY) {
                    System.out.println("Packet " + packetsSent + " lost.");
                    cwnd = 1; // Reset CWND to 1 on loss
                    break;
                }
                System.out.println("Packet " + packetsSent + " acknowledged.");
            }

            if (cwnd < MAX_WINDOW_SIZE) {
                cwnd *= 2; // Exponential growth in slow start
            }
        }

        System.out.println("Total packets sent: " + packetsSent);
    }

    // TCP Reno Simulation
    private static void simulateTCPReno() {
        int cwnd = 1; // Congestion Window Size (initial)
        int packetsSent = 0;
        int ssthresh = MAX_WINDOW_SIZE / 2; // Slow start threshold
        Random random = new Random();

        while (packetsSent < MAX_PACKETS) {
            System.out.println("CWND: " + cwnd);

            // Send packets in the current congestion window
            for (int i = 0; i < cwnd && packetsSent < MAX_PACKETS; i++) {
                packetsSent++;
                if (random.nextDouble() < LOSS_PROBABILITY) {
                    System.out.println("Packet " + packetsSent + " lost.");
                    ssthresh = cwnd / 2; // Update slow start threshold
                    cwnd = 1; // Reset CWND to 1 on loss
                    break;
                }
                System.out.println("Packet " + packetsSent + " acknowledged.");
            }

            if (cwnd < ssthresh) {
                cwnd *= 2; // Exponential growth in slow start
            } else {
                cwnd++; // Linear growth in congestion avoidance
            }

            if (cwnd > MAX_WINDOW_SIZE) {
                cwnd = MAX_WINDOW_SIZE; // Cap CWND at maximum window size
            }
        }

        System.out.println("Total packets sent: " + packetsSent);
    }
}