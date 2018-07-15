package D04_Froggy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Lake<Integer> lake = new Lake<>();
        while (true){
            String line = reader.readLine();
            if ("END".equals(line)){
                break;
            }

            Integer[] tokens = Arrays.stream(line.split("(|,)\\s+"))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            lake.setLake(tokens);
        }

        List<Integer> result = new ArrayList<>();
        for (Integer frog : lake) {
            result.add(frog);
        }

        for (int i = 0; i < result.size(); i++) {
            if (i == result.size() - 1){
                System.out.print(result.get(i));
                break;
            }

            System.out.print(result.get(i) + ", ");
        }
    }
}
