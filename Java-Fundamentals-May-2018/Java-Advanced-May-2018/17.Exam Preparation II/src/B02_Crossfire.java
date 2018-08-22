import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class B02_Crossfire {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] dimentions = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = dimentions[0];
        int cols = dimentions[1];

        List<List<Integer>> matrix = new ArrayList<>();
        fillMatrix(matrix, rows, cols);
        
        while (true){
            String line = reader.readLine();
            if ("Nuke it from orbit".equals(line)){
                break;
            }
            
            int[] tokens = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int row = tokens[0];
            int col = tokens[1];
            int radius = tokens[2];

            destroyColumn(matrix, row, col, radius);
            clearEmptyLines(matrix);
            destroyRow(matrix, row, col, radius);
            clearEmptyLines(matrix);
        }
        
        printMatrix(matrix);
    }

    private static void clearEmptyLines(List<List<Integer>> matrix) {
        matrix.removeIf(line -> line.isEmpty());
    }

    private static void destroyRow(List<List<Integer>> matrix, int row, int col, int radius) {
        for (int i = col - radius; i < col + radius ; i++) {
            if (isValidDimentions(row, i, matrix)){
                matrix.get(row).remove(i);
                i--;
            }
        }
    }

    private static void destroyColumn(List<List<Integer>> matrix, int row, int col, int radius) {
        for (int i = row - radius; i <= row + radius; i++) {
            if (isValidDimentions(i, col, matrix)){
                matrix.get(i).remove(col);
            }
        }
    }

    private static boolean isValidDimentions(int row, int col, List<List<Integer>> matrix) {
        return row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(row).size();
    }

//    private static void destroyRow(List<List<Integer>> matrix, int row, int col, int radius) {
//        for (int i = col - radius; i <= col + radius; i++) {
//            try {
//                matrix.get(row).remove(i);
//                i--;
//            } catch (Exception ignored){}
//        }
//
//        removeEmptyRows(matrix);
//    }
//
//    private static void removeEmptyRows(List<List<Integer>> matrix) {
//        for (int rowIndex = 0; rowIndex < matrix.size(); rowIndex++) {
//            if (matrix.get(rowIndex).size() == 0){
//                matrix.remove(rowIndex);
//                rowIndex--;
//            }
//        }
//    }
//
//    private static void destroyColumn(List<List<Integer>> matrix, int row, int col, int radius) {
//        for (int i = row - radius; i <= row + radius; i++) {
//            try {
//                matrix.get(i).remove(col);
//            } catch (Exception ignored){}
//        }
//
//        removeEmptyRows(matrix);
//    }

    private static void printMatrix(List<List<Integer>> matrix) {
        StringBuilder resultMatrix = new StringBuilder();
        for (List<Integer> row : matrix) {
            for (Integer col : row) {
                resultMatrix.append(col)
                        .append(" ");
            }

            resultMatrix.append(System.lineSeparator());
        }

        System.out.println(resultMatrix);
    }

    private static void fillMatrix(List<List<Integer>> matrix, int rows, int cols) {
        int counter = 0;
        for (int row = 0; row < rows; row++) {
            matrix.add(new ArrayList<>());
            for (int col = 0; col < cols; col++) {
                matrix.get(row).add(++counter);
            }
        }
    }
}
