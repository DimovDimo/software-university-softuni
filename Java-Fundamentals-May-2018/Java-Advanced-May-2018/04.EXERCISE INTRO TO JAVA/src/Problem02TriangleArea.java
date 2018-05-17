import java.util.Scanner;

public class Problem02TriangleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer firstPointX = scanner.nextInt();
        Integer firstPointY = scanner.nextInt();
        Integer secondPointX = scanner.nextInt();
        Integer secondPointY = scanner.nextInt();
        Integer thirdPointX = scanner.nextInt();
        Integer thirdPointY = scanner.nextInt();

        int area = Math.abs(
                (firstPointX*(secondPointY - thirdPointY) +
                        secondPointX*(thirdPointY-firstPointY) +
                        thirdPointX*(firstPointY-secondPointY)
                )/2
        );

        System.out.printf("%d", area);
    }
}
