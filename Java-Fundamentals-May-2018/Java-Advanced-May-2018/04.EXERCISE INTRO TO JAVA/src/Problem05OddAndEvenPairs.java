import java.util.Scanner;

public class Problem05OddAndEvenPairs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arrayOfStrings = scanner.nextLine()
                .split("\\s+");
        int[] arrayOfIntegers = new int[arrayOfStrings.length];
        for (int i=0; i < arrayOfStrings.length; i++) {
            arrayOfIntegers[i] = Integer.parseInt(arrayOfStrings[i]);
        }

        if (arrayOfIntegers.length % 2 != 0){
            System.out.println("invalid length");
        } else {
            for (int i = 1; i < arrayOfIntegers.length; i += 2) {
                int firstNimber = arrayOfIntegers[i - 1];
                int secondNumber = arrayOfIntegers[i];
                if (firstNimber % 2 == 0 && secondNumber % 2 == 0){
                    System.out.printf("%d, %d -> both are even%n", firstNimber, secondNumber);
                } else if (firstNimber % 2 != 0 && secondNumber % 2 != 0){
                    System.out.printf("%d, %d -> both are odd%n", firstNimber, secondNumber);
                } else {
                    System.out.printf("%d, %d -> different%n", firstNimber, secondNumber);
                }
            }
        }
    }
}
