package B02_LittleAlchemy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LittleAlchemy {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int gold = 0;

        ArrayDeque<Integer> stones = Arrays.stream(reader.readLine()
                .split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors
                        .toCollection(ArrayDeque::new));

        while (true){
            String line = reader.readLine();
            if ("Revision".equals(line)){
                break;
            }

            String[] commandTokens = line.split("\\s+");
            switch (commandTokens[0]){
                case "Apply":
                    int countAcid = Integer.parseInt(commandTokens[2]);
                    for (int i = 0; i < countAcid && stones.size() > 0; i++) {
                        int stone = stones.pop();
                        stone--;
                        if (stone == 0){
                            gold++;
                        } else {
                            stones.addLast(stone);
                        }
                    }
                    break;
                case "Air":
                    if (gold != 0){
                        gold--;
                        int stone = Integer.parseInt(commandTokens[2]);
                        stones.addLast(stone);
                    }
                    break;
            }
        }

        StringBuilder output = new StringBuilder();
        for (Integer stone : stones) {
            output.append(stone)
                    .append(" ");
        }
        System.out.println(output);
        System.out.println(gold);
    }
}
