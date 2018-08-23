package B02_Snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class Snake {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int sizeScreen = Integer.parseInt(reader.readLine());
        Deque<String> commands = new ArrayDeque<>();
        commands.addAll(Arrays.stream(reader.readLine().split(","))
        .collect(Collectors.toCollection(ArrayDeque::new)));

        String[][] screen = fillScreen(reader, sizeScreen);
    }

    private static String[][] fillScreen(BufferedReader reader, int sizeScreen) throws IOException {
        String[][] screen = new String[sizeScreen][sizeScreen];
        for (int row = 0; row < sizeScreen; row++) {
            String[] rowItems = reader.readLine().split(" ");
            for (int col = 0; col < sizeScreen; col++) {
                screen[row][col] = rowItems[col];
            }
        }

        return screen;
    }
}
