package A01_BitSnow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BitSnow {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[][] wonderland = fillWonderLand(reader);
        snowing(wonderland);
        printWonderLand(wonderland);
    }

    private static int[][] fillWonderLand(BufferedReader reader) throws IOException {
        String[] tokens = reader.readLine().split(", ");
        int[][] output = new int[tokens.length][16];
        for (int i = 0; i < tokens.length; i++) {
            String row = Integer.toBinaryString(Integer.parseInt(tokens[i]));
            row = makeString(16 - row.length()) + row;
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == '1'){
                    output[i][j] = 1;
                }
            }
        }

        return output;
    }

    private static void printWonderLand(int[][] wonderland) {
        System.out.println(Arrays.stream(wonderland)
         .map(arr -> Arrays.stream(arr).mapToObj(String::valueOf)
                .collect(Collectors.joining("")))
            .map(str -> Integer.parseInt(str, 2) + "")
            .collect(Collectors.joining(", ")));
    }

    private static void snowing(int[][] wonderland) {
        boolean isSnow = true;
        int counter = 0;
        while (isSnow){
            isSnow = false;
            for (int row = 0; row < wonderland.length - 1; row++) {
                for (int col = 0; col < wonderland[row].length; col++) {
                    if (wonderland[row][col] == 1){
                        if (wonderland[row + 1][col] == 0){
                            isSnow = true;
                            wonderland[row + 1][col] = 1;
                            wonderland[row][col] = 0;
                        }
                    }
                }
            }

            counter++;
            if (counter == 100) break;
//            for (List<String> strings : wonderland) {
//                System.out.println(strings);
//            }
//            System.out.println("======================");
        }
    }

    private static void fillWonderLand(BufferedReader reader, List<List<String>> wonderland) throws IOException {
        String[] tokens = reader.readLine().split(", ");
        for (String token : tokens) {
            String row = Integer.toBinaryString(Integer.parseInt(token));
//            if (row.length() < 16){
//                row = makeString(16 - row.length()) + row;
//            }
            row = makeString(16 - row.length()) + row;
            List<String> level = Arrays.stream(row.split("")).collect(Collectors.toCollection(ArrayList::new));
            wonderland.add(level);
        }
    }

    private static String makeString(int i) {
        StringBuilder output = new StringBuilder();
        for (int j = 0; j < i; j++) {
             output.append("0");
        }

        return output.toString();
    }
}
