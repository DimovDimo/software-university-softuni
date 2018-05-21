import java.util.Scanner;

public class Problem04MaximalSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        int rowsMatrix = Integer.parseInt(input[0]);
        int colsMatrix = Integer.parseInt(input[1]);
        int[][] matrix = new int[rowsMatrix][colsMatrix];
        readMatrix(scanner, matrix);
        int[][] bestMatrix = new int[3][3];
        int bestSum = Integer.MIN_VALUE;

        for (int row = 0; row <= matrix.length - 2; row++) {
            for (int col = 0; col < matrix[0].length - 2; col++) {
                int currentSum = 0;
                int[][] currentMatrix = new int[3][3];
                for (int currentRow = 0; currentRow < 3; currentRow++) {
                    for (int currentCol = 0; currentCol < 3; currentCol++) {
                        try {
                            currentSum += matrix[row + currentRow][col + currentCol];
                            currentMatrix[currentRow][currentCol] = matrix[row + currentRow][col + currentCol];
                        } catch (ArrayIndexOutOfBoundsException a){
                            System.out.printf("");
                        }

                    }
                }

                if (bestSum < currentSum){
                    bestSum = currentSum;
                    bestMatrix = currentMatrix;
                }
            }
        }

        System.out.printf("Sum = %d%n", bestSum);
        printMatrix(bestMatrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.printf("%d ", matrix[row][col]);
            }

            System.out.printf("%n");
        }
    }

    private static void walksMatrix(int[][] matrix, int[][] bestMatrix, int bestSum) {
        for (int row = 0; row < bestMatrix.length - 2; row++) {
            for (int col = 0; col < bestMatrix[0].length - 2; col++) {
                int currentSum = 0;
                int[][] currentMatrix = new int[3][3];
                for (int currentRow = 0; currentRow < 3; currentRow++) {
                    for (int currentCol = 0; currentCol < 3; currentCol++) {
                        currentSum += matrix[row + currentRow][col + currentCol];
                        currentMatrix[currentRow][currentCol] = matrix[row + currentRow][col + currentCol];
                    }
                }

                if (bestSum < currentSum){
                    bestSum = currentSum;
                    bestMatrix = currentMatrix;
                }
            }
        }

        System.out.printf("");
    }

    private static void readMatrix(Scanner scanner, int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            String[] currentRow = scanner.nextLine().split("\\s+");
            for (int col = 0; col < currentRow.length; col++) {
                matrix[row][col] = Integer.parseInt(currentRow[col]);
            }
        }
    }
}
