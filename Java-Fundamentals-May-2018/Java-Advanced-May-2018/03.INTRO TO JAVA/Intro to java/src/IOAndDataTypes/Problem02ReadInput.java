package IOAndDataTypes;

import java.util.Scanner;

public class Problem02ReadInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstWord = scanner.next("\\w+");
        String secondWord = scanner.next("\\w+");
        Integer firstInt = scanner.nextInt();
        Integer secondInt = scanner.nextInt();
        Integer thirdInt = scanner.nextInt();
        scanner.nextLine();
        String thirdWord = scanner.nextLine();

        Integer sumOfNumbers = firstInt + secondInt + thirdInt;

        System.out.println(firstWord + " " + secondWord
            + " " + thirdWord + " " + sumOfNumbers);
    }
}
