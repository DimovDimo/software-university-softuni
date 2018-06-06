import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class p06_ReverseAndExclude {
    public static void main(String[] args) throws IOException {
        BiFunction<List<Integer>, Integer, List<Integer>> reverseAndExclude = (inputNums, divisibleNum) -> {
            List<Integer> result = new LinkedList<>();
            inputNums.forEach(num -> {if(num % divisibleNum != 0){
            result.add(num);}
            });
            Collections.reverse(result);
            return result;
        };

        Consumer<List<Integer>> printNumbers = nums -> {
            List<String> output = new LinkedList<>();
            for (Integer num : nums) {
                output.add(num + "");
            }

            System.out.printf("%s%n", String.join(" ", output));};

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split("\\s+");
        List<Integer> numersInput = new LinkedList<>();
        for (int i = 0; i < inputs.length; i++) {
            numersInput.add(Integer.parseInt(inputs[i]));
        }

        Integer numForDivisible = Integer.parseInt(reader.readLine());
        List<Integer> outputNums = reverseAndExclude.apply(numersInput, numForDivisible);
        printNumbers.accept(outputNums);
    }
}
