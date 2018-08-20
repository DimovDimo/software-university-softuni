import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A01_Shockwave {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String[] matrixSizes = reader.readLine().split("\\s+");
        int rows = Integer.parseInt(matrixSizes[0]);
        int cols = Integer.parseInt(matrixSizes[1]);
        int[][] matrix = new int[rows][cols];
        
        while (true){
            String line = reader.readLine();
            if ("Here We Go".equals(line)){
                break;
            }

            String[] cordinatesTokens = line.split("\\s+");
            int x1 = Integer.parseInt(cordinatesTokens[0]);
            int y1 = Integer.parseInt(cordinatesTokens[1]);
            int x2 = Integer.parseInt(cordinatesTokens[2]);
            int y2 = Integer.parseInt(cordinatesTokens[3]);

            for (int row = x1; row <= x2; row++) {
                for (int col = y1; col <= y2; col++) {
                    matrix[row][col]++;
                }
            }
        }

        StringBuilder output = new StringBuilder();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                output.append(matrix[row][col] + " ");
            }
            output.append(System.lineSeparator());
        }

        System.out.println(output);
    }
}
