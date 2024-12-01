import java.util.HashMap;
import java.util.Scanner;

public class ARP {
    public static void main(String[] args) {
        // Predefined ARP Table: IP Address to MAC Address mapping
        HashMap<String, String> arpTable = new HashMap<>();
        arpTable.put("192.168.0.1", "00-14-22-01-23-45");
        arpTable.put("192.168.0.2", "00-14-22-01-23-46");
        arpTable.put("192.168.0.3", "00-14-22-01-23-47");
        arpTable.put("192.168.0.4", "00-14-22-01-23-48");

        Scanner scanner = new Scanner(System.in);

        System.out.println("ARP Protocol Simulation");
        System.out.println("Enter an IP address to find its corresponding MAC address (type 'exit' to quit):");

        while (true) {
            System.out.print("IP Address: ");
            String ipAddress = scanner.nextLine();

            if (ipAddress.equalsIgnoreCase("exit")) {
                System.out.println("Exiting ARP simulation. Goodbye!");
                break;
            }

            // Look up the MAC address in the ARP table
            String macAddress = arpTable.get(ipAddress);

            if (macAddress != null) {
                System.out.println("MAC Address for " + ipAddress + ": " + macAddress);
            } else {
                System.out.println("IP Address " + ipAddress + " not found in the ARP table.");
            }
        }

        scanner.close();
    }
}