import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class p03_CustomMinFunction {
    public static void main(String[] args) throws IOException {
        Function<Integer[], Integer> getMin = numbers -> {
            int min = Integer.MAX_VALUE;
            for (Integer number : numbers) {
                if (min > number){
                    min = number;
                }
            }

            return min;
        };

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split("\\s+");
        Integer[] words = new Integer[inputs.length];
        for (int i = 0; i < words.length; i++) {
            words[i] = Integer.parseInt(inputs[i]);
        }

        System.out.print(getMin.apply(words));
    }
}
