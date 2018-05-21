import java.util.Scanner;

public class Problem01FillTheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(", ");
        int sizeNxNMatrix = Integer.parseInt(input[0]);
        int[][] matrix = new int[sizeNxNMatrix][sizeNxNMatrix];
        String pattern = input[1];
        if (pattern.equals("A")){
            patternA(matrix);
        } else {
            patternB(matrix);
        }

        printMatrix(matrix);
    }

    private static void patternB(int[][] matrix) {
        int counterMatrix = 1;
        for (int col = 0; col < matrix[0].length; col++) {
            if (col % 2 == 0){
                for (int row = 0; row < matrix.length; row++) {
                    matrix[row][col] = counterMatrix;
                    counterMatrix++;
                }
            } else {
                for (int row = matrix.length - 1; row >= 0; row--) {
                    matrix[row][col] = counterMatrix;
                    counterMatrix++;
                }
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.printf("%d ", matrix[row][col]);
            }

            System.out.printf("%n");
        }
    }

    private static void patternA(int[][] matrix) {
        int counterMatrix = 1;
        for (int col = 0; col < matrix[0].length; col++) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][col] = counterMatrix;
                counterMatrix++;
            }
        }
    }
}
