import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class p01SortEvenNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = getNumbers(reader);
        numbers.removeIf(n -> n % 2 != 0);
        printNumbers(numbers);
        numbers.sort((a, b) -> a.compareTo(b));
        printNumbers(numbers);
    }

    private static void printNumbers(List<Integer> numbers) {
        System.out.println(numbers.toString().substring(1, numbers.toString().length() - 1));
    }

    private static List<Integer> getNumbers(BufferedReader reader) throws IOException {
        String[] input = reader.readLine().split(", ");
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            numbers.add(Integer.parseInt(input[i]));
        }
        return numbers;
    }
}
