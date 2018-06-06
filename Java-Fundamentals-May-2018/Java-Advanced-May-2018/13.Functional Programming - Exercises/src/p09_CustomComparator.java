import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class p09_CustomComparator {
    public static void main(String[] args) throws IOException {
        Function<Integer[], Integer[]> filterEnenNumbers = nums -> {
            List<Integer> result = new LinkedList<>();
            for (Integer num : nums) {
                if (num % 2 == 0){
                    result.add(num);
                }
            }

            Integer[] output = new Integer[result.size()];
            for (int i = 0; i < output.length; i++) {
                output[i] = result.get(i);
            }

            return output;
        };

        Function<Integer[], Integer[]> filterOddNumbers = nums -> {
            List<Integer> result = new LinkedList<>();
            for (Integer num : nums) {
                if (num % 2 != 0){
                    result.add(num);
                }
            }

            Integer[] output = new Integer[result.size()];
            for (int i = 0; i < output.length; i++) {
                output[i] = result.get(i);
            }

            return output;
        };

        Consumer<Integer[]> printNumbers = nums -> {
            List<String> output = new LinkedList<>();
            for (Integer num : nums) {
                output.add(num + "");
            }

            System.out.printf("%s ", String.join(" ", output));};

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split("\\s+");
        Integer[] numersInput = new Integer[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            numersInput[i] = Integer.parseInt(inputs[i]);
        }

        Integer[] evenNumbers = filterEnenNumbers.apply(numersInput);
        Integer[] oddNumbers = filterOddNumbers.apply(numersInput);
        Arrays.sort(evenNumbers);
        Arrays.sort(oddNumbers);
        printNumbers.accept(evenNumbers);
        printNumbers.accept(oddNumbers);
    }
}
