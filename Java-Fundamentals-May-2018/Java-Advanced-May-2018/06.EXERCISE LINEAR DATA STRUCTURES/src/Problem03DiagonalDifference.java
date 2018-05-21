import java.util.Scanner;

public class Problem03DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeOnRowAndCol = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[sizeOnRowAndCol][sizeOnRowAndCol];
        readMatrix(scanner, matrix);
        int sumPrimaryDiagonal = getSumPrimaryDiagonal(matrix);
        int sumSecondaryDiagonal = getSumSecondaryDiagonal(matrix, sizeOnRowAndCol);
        int diagonalDifference = Math.abs(sumPrimaryDiagonal - sumSecondaryDiagonal);
        System.out.println(diagonalDifference);
    }

    private static void readMatrix(Scanner scanner, int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            String[] currentRow = scanner.nextLine().split("\\s+");
            for (int col = 0; col < currentRow.length; col++) {
                matrix[row][col] = Integer.parseInt(currentRow[col]);
            }
        }
    }

    private static int getSumSecondaryDiagonal(int[][] matrix, int sizeOnRowAndCol) {
        int sumSecondaryDiagonal = 0;
        for (int row = 0; row < matrix.length; row++) {
            int col = sizeOnRowAndCol - 1 -row;
            sumSecondaryDiagonal += matrix[row][col];
        }

        return sumSecondaryDiagonal;
    }

    private static int getSumPrimaryDiagonal(int[][] matrix) {
        int sumPrimaryDiagonal = 0;
        for (int rowAndCol = 0; rowAndCol < matrix.length; rowAndCol++) {
            sumPrimaryDiagonal += matrix[rowAndCol][rowAndCol];
        }

        return sumPrimaryDiagonal;
    }
}
