import java.util.Scanner;

public class Problem04CalculateExpression {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double f1 = Math.pow(((a*a + b*b)/(a*a - b*b)),
                ((a + b + c)/(Math.sqrt(c))));

        double f2 = Math.pow((a*a + b*b - Math.pow(c,3)),
                (a-b));

        double avgABC = (a + b + c) / 3;
        double avgF1AndF2 = (f1 + f2) / 2;
        double diff = Math.abs(avgABC - avgF1AndF2);

        System.out.printf("F1 result: %.2f; F2 result: %.2f; Diff: %.2f",
                f1, f2, diff);
    }
}
