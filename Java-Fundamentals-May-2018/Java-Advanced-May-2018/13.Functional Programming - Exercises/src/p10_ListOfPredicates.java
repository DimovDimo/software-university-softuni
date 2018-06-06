import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.BiPredicate;

public class p10_ListOfPredicates {
    public static void main(String[] args) throws IOException {
        BiPredicate<Integer, Integer[]> isDivisibleOfListOfNumbers = (number, numbers) -> {
            for (Integer currentNum : numbers) {
                if (number % currentNum != 0){
                    return false;
                }
            }

            return true;
        };

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int range = Integer.parseInt(reader.readLine());
        String[] inputs = reader.readLine().split("\\s+");
        Integer[] numersInput = new Integer[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            numersInput[i] = Integer.parseInt(inputs[i]);
        }

        for (int i = 1; i <= range; i++) {
            if (isDivisibleOfListOfNumbers.test(i, numersInput)){
                System.out.print(i + " ");
            };
        }
    }
}
