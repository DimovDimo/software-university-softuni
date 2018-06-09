import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class p08_BoundedNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> bounds = getReadNumbers(reader);
        List<Integer> numbers = getReadNumbers(reader);
        int lowerBound = Math.min(bounds.get(0), bounds.get(1));
        int upperBound = Math.max(bounds.get(0), bounds.get(1));
        printResult(numbers, lowerBound, upperBound);
    }

    private static void printResult(List<Integer> numbers, int lowerBound, int upperBound) {
        numbers.stream()
                .filter(n -> lowerBound <= n && n <= upperBound)
                .forEach(n -> System.out.print(n + " "));
    }

    private static List<Integer> getReadNumbers(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
