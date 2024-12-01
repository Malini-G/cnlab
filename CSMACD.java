import java.util.Random;
import java.util.Scanner;

public class CSMACD {

    // Function to generate CRC
    public static String generateCRC(String data, String polynomial) {
        int dataLen = data.length();
        int polyLen = polynomial.length();
        String dividend = data + "0".repeat(polyLen - 1);

        String remainder = divide(dividend, polynomial);
        return data + remainder; // Encoded frame
    }

    // Function to perform division for CRC
    public static String divide(String dividend, String divisor) {
        int len = divisor.length();
        String temp = dividend.substring(0, len);

        while (len < dividend.length()) {
            if (temp.charAt(0) == '1') {
                temp = xor(temp, divisor) + dividend.charAt(len);
            } else {
                temp = xor(temp, "0".repeat(len)) + dividend.charAt(len);
            }
            len++;
            temp = temp.substring(1);
        }

        // Perform division one last time
        if (temp.charAt(0) == '1') {
            temp = xor(temp, divisor);
        } else {
            temp = xor(temp, "0".repeat(len));
        }

        return temp.substring(1);
    }

    // XOR function for division
    public static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    // Function to simulate CSMA/CD Protocol
    public static void simulateCSMACD(String data, String polynomial, boolean introduceError) {
        System.out.println("Original Data: " + data);
        String encodedFrame = generateCRC(data, polynomial);
        System.out.println("Encoded Frame with CRC: " + encodedFrame);

        // Introduce error if required
        if (introduceError) {
            Random random = new Random();
            int errorPosition = random.nextInt(encodedFrame.length());
            char[] frameArray = encodedFrame.toCharArray();
            frameArray[errorPosition] = frameArray[errorPosition] == '0' ? '1' : '0'; // Flip bit
            encodedFrame = new String(frameArray);
            System.out.println("Frame with Error Introduced: " + encodedFrame);
        }

        // Verify CRC at receiver
        String remainder = divide(encodedFrame, polynomial);
        if (remainder.contains("1")) {
            System.out.println("Error detected in received frame!");
        } else {
            System.out.println("No error detected. Frame received successfully!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter binary data: ");
        String data = scanner.nextLine();

        System.out.println("Enter CRC Polynomial (binary): ");
        String polynomial = scanner.nextLine();

        System.out.println("Simulating CSMA/CD with no error:");
        simulateCSMACD(data, polynomial, false);

        System.out.println("\nSimulating CSMA/CD with error:");
        simulateCSMACD(data, polynomial, true);

        scanner.close();
    }
}
