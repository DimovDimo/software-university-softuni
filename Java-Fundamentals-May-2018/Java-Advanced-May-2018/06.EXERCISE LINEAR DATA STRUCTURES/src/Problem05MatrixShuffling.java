import java.util.Scanner;

public class Problem05MatrixShuffling {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String[] input = scanner.nextLine().split("\\s+");
            int rowsMatrix = Integer.parseInt(input[0]);
            int colsMatrix = Integer.parseInt(input[1]);
            String[][] matrix = new String[rowsMatrix][colsMatrix];
            readMatrix(scanner, matrix);
            swapMatrix(scanner, matrix);
        } catch (ArrayIndexOutOfBoundsException a){
            printInvalidInput();
        }

    }

    private static void swapMatrix(Scanner scanner, String[][] matrix) {
        while (true){
            String line = scanner.nextLine();
            if (line.equals("END")){
                break;
            }

            String[] comands = line.split("\\s+");
            if (comands[0].equals("swap")){
                int row1 = Integer.parseInt(comands[1]);
                int col1 = Integer.parseInt(comands[2]);
                int row2 = Integer.parseInt(comands[3]);
                int col2 = Integer.parseInt(comands[4]);
                try {
                    String temporalElementFromMatrix = matrix[row1][col1];
                    matrix[row1][col1] = matrix[row2][col2];
                    matrix[row2][col2] = temporalElementFromMatrix;
printMatrix(matrix);
                } catch (ArrayIndexOutOfBoundsException a){
                    printInvalidInput();
                }
            } else {
                printInvalidInput();
            }
        }
    }

    private static void printInvalidInput() {
        System.out.printf("Invalid input!%n");
    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.printf("%s ", matrix[row][col]);
            }

            System.out.printf("%n");
        }
    }

    private static void readMatrix(Scanner scanner, String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            String[] currentRow = scanner.nextLine().split("\\s+");
            for (int col = 0; col < currentRow.length; col++) {
                matrix[row][col] = currentRow[col];
            }
        }
    }
}
