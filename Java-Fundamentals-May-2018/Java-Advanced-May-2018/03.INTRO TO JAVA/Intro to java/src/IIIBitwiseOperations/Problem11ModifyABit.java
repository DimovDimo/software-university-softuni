package IIIBitwiseOperations;

import java.util.Scanner;

public class Problem11ModifyABit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int position = scanner.nextInt();
        int value = scanner.nextInt();
        int mask = 0;
        int result = 0;

        if (value == 0){
            mask = ~(1 << position);
            result = number & mask;
        } else {
            mask = 1 << position;
            result = number | mask;
        }

        System.out.println(result);
    }
}
