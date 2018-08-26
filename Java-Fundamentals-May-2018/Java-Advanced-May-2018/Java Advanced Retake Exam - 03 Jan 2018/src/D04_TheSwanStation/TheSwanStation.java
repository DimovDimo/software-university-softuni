package D04_TheSwanStation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TheSwanStation {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayDeque<Integer> theNumbers = new ArrayDeque<>();
        Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(theNumbers::add);

        ArrayDeque<Integer> targets = new ArrayDeque<>();
        Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(targets::add);

        List<Integer> output = new ArrayList<>();
        while (!theNumbers.isEmpty()){
            int theNumber = theNumbers.peek();
            int target = targets.remove();

            if (target % theNumber == 0){
                output.add(target);
                theNumbers.remove();
            } else {
                target++;
                targets.add(target);
            }
        }

        System.out.println(output.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
    }
}
