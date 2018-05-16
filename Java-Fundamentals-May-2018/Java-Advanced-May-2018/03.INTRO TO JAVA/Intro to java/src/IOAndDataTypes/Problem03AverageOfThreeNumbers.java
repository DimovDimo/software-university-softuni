package IOAndDataTypes;

import java.util.Scanner;

public class Problem03AverageOfThreeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double firstNum = scanner.nextDouble();
        double secondNum = scanner.nextDouble();
        double thirdNum = scanner.nextDouble();
        double sumOfNumbers = firstNum + secondNum + thirdNum;
        double average = sumOfNumbers / 3.0;

        System.out.printf("%.2f", average);
    }
}
