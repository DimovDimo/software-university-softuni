import java.util.Scanner;

public class Problem02MatrixOfPalindromes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        int rowsMatrix = Integer.parseInt(input[0]);
        int colsMatrix = Integer.parseInt(input[1]);
        String[][] matrix = new String[rowsMatrix][colsMatrix];
        fillMatrix(matrix);
        printMatrix(matrix);
    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.printf("%s ", matrix[row][col]);
            }

            System.out.printf("%n");
        }
    }

    private static void fillMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                String rowChar = (char) ('a' + row) + "";
                String colChar = (char) ('a' + row + col) + "";
                matrix[row][col] = rowChar + colChar + rowChar;
            }
        }
    }
}
