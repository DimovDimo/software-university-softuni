import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem06StringMatrixRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rotationCount = getCountRotation(scanner);
        List<String> words = new ArrayList<>();
        readWords(scanner, words);
        int maxlenghtOfString = findMaxLenghtOfStringInList(words);
        char[][] matrix = new char[words.size()][maxlenghtOfString];
        fillMatrix(words, matrix);
        matrix = matrixRotations(rotationCount, matrix);
        printMatrix(matrix);
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.printf("%c", matrix[row][col]);
            }

            System.out.printf("%n");
        }
    }

    private static char[][] matrixRotations(int rotationCount, char[][] matrix) {
        for (int i = 0; i < rotationCount; i++) {
            matrix = singleRotation(matrix);
        }

        return matrix;
    }

    private static char[][] singleRotation(char[][] matrix) {
        int oldMatrixCols = matrix[0].length;
        int oldMatrixRows = matrix.length;
        char[][] resultMatrix = new char[oldMatrixCols][oldMatrixRows];
        for (int col = 0; col < resultMatrix.length; col++) {
            for (int row = matrix.length; row > 0; row--) {
                int resultMatrixCol = matrix.length - row;
                resultMatrix[col][resultMatrixCol] = matrix[row-1][col];
            }
        }

        return resultMatrix;
    }

    private static void fillMatrix(List<String> words, char[][] matrix) {
        String[] arrayWords = new String[words.size()];
        words.toArray(arrayWords);
        for (int row = 0; row < matrix.length; row++) {
            char[] letters = arrayWords[row].toCharArray();
            for (int col = 0; col < matrix[0].length; col++) {
                if (arrayWords[row].length() > col){
                    matrix[row][col] = letters[col];
                } else {
                    matrix[row][col] = ' ';
                }
            }
        }
    }

    private static int findMaxLenghtOfStringInList(List<String> words) {
        int maxlenghtOfString = 0;
        for (String word : words) {
            if (maxlenghtOfString < word.length()){
                maxlenghtOfString = word.length();
            }
        }

        return maxlenghtOfString;
    }

    private static void readWords(Scanner scanner, List<String> words) {
        String word = scanner.nextLine();
        while (!word.equals("END")){
            words.add(word);
            word = scanner.nextLine();
        }
    }

    private static int getCountRotation(Scanner scanner) {
        String[] input = scanner.nextLine().split("Rotate\\(");
        int rotationDegrees = Integer.parseInt(input[1].substring(0, input[1].length()-1));
        return rotationDegrees/90;
    }
}
