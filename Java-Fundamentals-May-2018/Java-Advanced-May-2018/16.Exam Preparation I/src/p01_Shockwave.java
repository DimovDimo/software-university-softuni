import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class p01_Shockwave {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> tokensMatrix = getLineToList(reader);
        int[][] matrix = new int[tokensMatrix.get(0)][tokensMatrix.get(1)];
        while (true){
            String line = reader.readLine();
            if ("Here We Go".equals(line)){
                break;
            }

            makeShockwave(matrix, line);
        }

        printMatrix(matrix);
    }

    private static void makeShockwave(int[][] matrix, String line) {
        List<Integer> cordinatesMatrix = Arrays.stream(line.split("\\s+"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        int x1 = cordinatesMatrix.get(0);
        int y1 = cordinatesMatrix.get(1);
        int x2 = cordinatesMatrix.get(2);
        int y2 = cordinatesMatrix.get(3);

        for (int row = x1; row <= x2; row++) {
            for (int col = y1; col <= y2; col++) {
                matrix[row][col]++;
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.printf("%d ", matrix[row][col]);
            }

            System.out.printf("%n");
        }
    }

    private static List<Integer> getLineToList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
