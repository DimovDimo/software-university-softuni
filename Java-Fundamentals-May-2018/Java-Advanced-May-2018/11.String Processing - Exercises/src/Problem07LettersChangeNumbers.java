import java.util.Scanner;

public class Problem07LettersChangeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split("\\s+");
        double totalSum = getTotalSum(words);
        printResult(totalSum);
    }

    private static double getTotalSum(String[] words) {
        double totalSum = 0;
        for (String word : words) {
            char firstLetter = word.charAt(0);
            char lastLetter = word.charAt(word.length() - 1);
            double number = Double.parseDouble(word.substring(1, word.length() - 1));
            if (Character.isUpperCase(firstLetter)){
                int position = firstLetter - 64;
                number /= position;
            } else {
                int position = firstLetter - 96;
                number *= position;
            }

            if (Character.isUpperCase(lastLetter)){
                int position = lastLetter - 64;
                number -= position;
            } else {
                int position = lastLetter - 96;
                number += position;
            }

            totalSum += number;
        }

        return totalSum;
    }

    private static void printResult(double totalSum) {
        System.out.printf("%.2f", totalSum);
    }
}
