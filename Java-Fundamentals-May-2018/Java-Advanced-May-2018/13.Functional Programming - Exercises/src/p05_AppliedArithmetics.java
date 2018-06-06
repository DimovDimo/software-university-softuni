import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class p05_AppliedArithmetics {
    public static void main(String[] args) throws IOException {
        Function<List<Integer>, List<Integer>> addOne = nums -> {
            List<Integer> result = new LinkedList<>();
            nums.forEach(num -> result.add(num + 1));
            return result;
        };

        Function<List<Integer>, List<Integer>> multipliesByTwo = nums -> {
            List<Integer> result = new LinkedList<>();
            nums.forEach(num -> result.add(num * 2));
            return result;
        };

        Function<List<Integer>, List<Integer>> substractOne = nums -> {
            List<Integer> result = new LinkedList<>();
            nums.forEach(num -> result.add(num - 1));
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

        while (true){
            String line = reader.readLine();
            if ("end".equals(line)){
                break;
            }

            switch (line){
                case "add":
                    numersInput = addOne.apply(numersInput);
                    break;
                case "multiply":
                    numersInput = multipliesByTwo.apply(numersInput);
                    break;
                case "subtract":
                    numersInput = substractOne.apply(numersInput);
                    break;
                case "print":
                    printNumbers.accept(numersInput);
                    break;
                default:
                    break;
            }
        }
    }
}
