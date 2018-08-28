package B02_BombField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BombField {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());
        String[][] bombField = new String[size][size];
        List<String> commands = Arrays.stream(reader.readLine().split(",")).collect(Collectors.toList());

        int currentRow = -1;
        int currentCol = -1;
        int leftBombs = 0;

        for (int row = 0; row < bombField.length; row++) {
            String[] fieldTokens = reader.readLine().split(" ");
            for (int col = 0; col < bombField[row].length; col++) {
                bombField[row][col] = fieldTokens[col];
                if ("s".equals(fieldTokens[col])){
                    currentRow = row;
                    currentCol = col;
                } else if ("B".equals(fieldTokens[col])){
                    leftBombs++;
                }
            }
        }

        boolean isEnd = false;
        boolean isEmptyCommands = false;
        while (true){

            if (commands.isEmpty()){
                isEmptyCommands = true;
                break;
            }

            String command = commands.remove(0);

            switch (command){
                case "up":
                    if (currentRow - 1 >= 0){
                        currentRow--;
                    }
                    break;
                case "down":
                    if (currentRow + 1 < size){
                        currentRow++;
                    }
                    break;
                case "left":
                    if (currentCol - 1 >= 0){
                        currentCol--;
                    }
                    break;
                case "right":
                    if (currentCol + 1 < size){
                        currentCol++;
                    }
                    break;
            }

            if ("B".equals(bombField[currentRow][currentCol])){
                bombField[currentRow][currentCol] = "+";
                System.out.println("You found a bomb!");
                leftBombs--;
            } else if ("e".equals(bombField[currentRow][currentCol])){
                isEnd = true;
                break;
            }
        }

        if (leftBombs == 0){
            System.out.println("Congratulations! You found all bombs!");
        } else if (isEnd){
            System.out.printf("END! %d bombs left on the field", leftBombs);
        } else if (isEmptyCommands){
            System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)", leftBombs, currentRow, currentCol);
        }
    }
}
