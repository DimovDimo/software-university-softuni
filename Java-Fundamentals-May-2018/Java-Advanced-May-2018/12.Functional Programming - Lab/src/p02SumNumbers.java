import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class p02SumNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Function<String, Integer> parseInt = stringInput -> Integer.parseInt(stringInput);
        String[] input = reader.readLine().split(", ");
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            numbers.add(parseInt.apply(input[i]));
        }

        System.out.printf("Count = %d%n", numbers.size());
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }

        System.out.printf("Sum = %d", sum);
    }
}
