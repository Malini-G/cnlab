import java.util.Scanner;

public class CRC {
    // XOR operation for CRC
    public static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < b.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    // Perform CRC division
    public static String divide(String dividend, String divisor) {
        int pick = divisor.length();
        String temp = dividend.substring(0, pick);

        while (pick < dividend.length()) {
            if (temp.charAt(0) == '1') {
                temp = xor(divisor, temp) + dividend.charAt(pick);
            } else {
                temp = xor("0".repeat(divisor.length()), temp) + dividend.charAt(pick);
            }
            pick += 1;
        }

        if (temp.charAt(0) == '1') {
            temp = xor(divisor, temp);
        } else {
            temp = xor("0".repeat(divisor.length()), temp);
        }

        return temp;
    }

    // Generate CRC code
    public static String generateCRC(String data, String generator) {
        int dataLength = data.length();
        int generatorLength = generator.length();

        String appendedData = data + "0".repeat(generatorLength - 1);
        String remainder = divide(appendedData, generator);

        return data + remainder;
    }

    // Check received data for errors
    public static boolean checkCRC(String dataWithCRC, String generator) {
        String remainder = divide(dataWithCRC, generator);
        return remainder.equals("0".repeat(generator.length() - 1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the data bits: ");
        String data = scanner.nextLine();

        System.out.print("Enter the generator polynomial: ");
        String generator = scanner.nextLine();

        // Generate CRC code
        String dataWithCRC = generateCRC(data, generator);
        System.out.println("Transmitted data with CRC: " + dataWithCRC);

        // Simulate receiving the message with an option for errors
        System.out.print("Enter the received data (with CRC code): ");
        String receivedData = scanner.nextLine();

        // Check for errors
        boolean isValid = checkCRC(receivedData, generator);
        if (isValid) {
            System.out.println("No errors detected.");
        } else {
            System.out.println("Error detected in the received data.");
        }

        scanner.close();
    }
}