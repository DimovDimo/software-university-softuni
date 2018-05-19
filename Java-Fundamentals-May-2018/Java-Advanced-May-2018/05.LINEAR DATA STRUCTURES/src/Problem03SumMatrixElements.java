import java.util.Scanner;

public class Problem03SumMatrixElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] matrixRowAndCol = scanner.nextLine()
                .split(", ");
        int rowM = Integer.parseInt(matrixRowAndCol[0]);
        int colM = Integer.parseInt(matrixRowAndCol[1]);
        int[][] matrix = new int[rowM][colM];

        for (int row = 0; row < matrix.length; row++) {
            String[] remider = scanner.nextLine().split(", ");
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = Integer.parseInt(remider[col]);
            }
        }

        int sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                sum += matrix[row][col];
            }
        }

        System.out.printf("%d%n%d%n%d", rowM, colM, sum);
    }
}
