package IIIBitwiseOperations;

import java.util.Scanner;

public class Problem10ExtractBitFromInteger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer number = scanner.nextInt();
        Integer position = scanner.nextInt();

        int mask = number >> position;
        int result = 1 & mask;

        System.out.println(result);
    }
}
