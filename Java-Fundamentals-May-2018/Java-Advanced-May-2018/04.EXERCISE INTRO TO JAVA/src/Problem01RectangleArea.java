import java.util.Scanner;

public class Problem01RectangleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double sideA = scanner.nextDouble();
        double sideB = scanner.nextDouble();
        double area = sideA * sideB;

        System.out.printf("%.2f", area);
    }
}
