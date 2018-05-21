import java.util.Scanner;

public class Problem13BlurFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long blurAmount = Long.parseLong(scanner.nextLine());
        String[] matrixRowsAndCols = scanner.nextLine().split("\\s+");
        long matrixRows = Long.parseLong(matrixRowsAndCols[0]);
        long matrixCols = Long.parseLong(matrixRowsAndCols[1]);
        long[][] matrix = new long[(int)matrixRows][(int)matrixCols];
        readMatrix(scanner, matrix);
        blurAmoutMatrix(scanner, blurAmount, matrix);
        prlongMatrix(matrix);
    }

    private static void prlongMatrix(long[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.printf("%d ", matrix[row][col]);
            }

            System.out.printf("%n");
        }
    }

    private static void blurAmoutMatrix(Scanner scanner, long blurAmount, long[][] matrix) {
        String[] targetAsStrings = scanner.nextLine().split("\\s+");
        int targetRow = Integer.parseInt(targetAsStrings[0]);
        int targetCol = Integer.parseInt(targetAsStrings[1]);
        for (int row = targetRow-1; row <= targetRow+1; row++) {
            for (int col = targetCol-1; col <= targetCol+1; col++) {
                if (row >= 0 && col >= 0 && row < matrix.length && col < matrix[0].length){
                    matrix[row][col] += blurAmount;
                }
            }
        }
    }

    private static void readMatrix(Scanner scanner, long[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            String[] currentRow = scanner.nextLine().split("\\s+");
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = Long.parseLong(currentRow[col]);
            }
        }
    }
}
