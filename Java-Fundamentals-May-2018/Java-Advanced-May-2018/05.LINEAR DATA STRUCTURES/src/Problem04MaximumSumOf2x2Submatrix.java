import java.util.Scanner;

public class Problem04MaximumSumOf2x2Submatrix {
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

        int bestSum = 0;
        int[][] bestSubmatrix = new int[2][2];
        for (int row = 0; row < matrix.length - 1; row++) {
            for (int col = 0; col < matrix[0].length - 1; col++) {
                int[][] currentSubmatrix = new int[][]{
                        {matrix[row][col], matrix[row][col+1]},
                        {matrix[row+1][col], matrix[row+1][col+1]}
                };

                int currentSum = 0;
                for (int[] submatrix : currentSubmatrix) {
                    for (int currentElement : submatrix) {
                        currentSum += currentElement;
                    }
                }

                if (currentSum > bestSum){
                    bestSubmatrix = currentSubmatrix;
                    bestSum = currentSum;
                }
            }
        }

        for (int[] submatrix : bestSubmatrix) {
            for (int currentElement : submatrix) {
                System.out.printf("%d ", currentElement);
            }

            System.out.printf("%n");
        }

        System.out.print(bestSum);
    }
}
