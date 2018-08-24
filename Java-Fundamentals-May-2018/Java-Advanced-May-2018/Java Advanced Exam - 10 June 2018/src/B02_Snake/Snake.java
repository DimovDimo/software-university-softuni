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
        commands.addAll(Arrays.stream(reader.readLine().split(", "))
        .collect(Collectors.toCollection(ArrayDeque::new)));

        String[][] screen = fillScreen(reader, sizeScreen);
        int currentRow = -1;
        int currentCol = -1;
        boolean isStartPositionFound = false;
        for (int row = 0; row < screen.length; row++) {
            for (int col = 0; col < screen[row].length; col++) {
                if (screen[row][col].equals("s")){
                    currentRow = row;
                    currentCol = col;
                    isStartPositionFound = true;
                    break;
                }
            }

            if (isStartPositionFound){
                break;
            }
        }

        int snakeLenght = 1;
        boolean didKill = false;
        while (!commands.isEmpty()){
            String command = commands.remove();

            switch (command){
                case "left":
                    if (currentCol == 0){
                        currentCol = screen[currentRow].length - 1;
                    } else {
                        currentCol--;
                    }
                    break;
                case "right":
                    if (screen[currentRow].length - 1 == currentCol){
                        currentCol = 0;
                    } else {
                        currentCol++;
                    }
                    break;
                case "up":
                    if (currentRow == 0){
                        currentRow = screen.length - 1;
                    } else {
                        currentRow--;
                    }
                    break;
                case "down":
                    if (currentRow == screen.length - 1){
                        currentRow = 0;
                    } else {
                        currentRow++;
                    }
                    break;
            }

            if (currentRow == -1 || currentCol == -1){
                break;
            }

            if (screen[currentRow][currentCol].equals("f")){
                screen[currentRow][currentCol] = "*";
                snakeLenght++;
            } else if (screen[currentRow][currentCol].equals("e")){
                didKill = true;
                break;
            }
        }

        if (didKill){
            System.out.println("You lose! Killed by an enemy!");
        } else {
            int leftFoood = 0;
            for (int row = 0; row < screen.length; row++) {
                for (int col = 0; col < screen[row].length; col++) {
                    if (screen[row][col].equals("f")){
                        leftFoood++;
                    }
                }
            }

            if (leftFoood == 0){
                System.out.printf("You win! Final snake length is %d", snakeLenght);
            } else {
                System.out.printf("You lose! There is still %d food to be eaten.", leftFoood);
            }
        }
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
