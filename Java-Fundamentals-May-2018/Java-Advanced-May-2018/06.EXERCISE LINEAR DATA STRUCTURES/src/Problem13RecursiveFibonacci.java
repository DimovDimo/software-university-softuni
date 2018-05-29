import java.util.Scanner;

public class Problem13RecursiveFibonacci {
    private static int[] memo;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfRecursiveFibonacci = scanner.nextInt();
        long result = getFibonacci(numberOfRecursiveFibonacci);
        System.out.println(result);
    }

    public static long getFibonacci(int n) {
        long[] fibonacciNumbers = new long[n + 1];

        fibonacciNumbers[0] = 1;
        fibonacciNumbers[1] = 1;
        for (int i = 2; i <= n; i++) {
            fibonacciNumbers[i] =
                    fibonacciNumbers[i - 1] + fibonacciNumbers[i - 2];
        }

        return fibonacciNumbers[n];
    }
}
