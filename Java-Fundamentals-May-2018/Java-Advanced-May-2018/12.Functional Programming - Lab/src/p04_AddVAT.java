import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class p04_AddVAT {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(", ");
        List<Double> numbers = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            numbers.add(Double.parseDouble(input[i]));
        }

        Function<Double, Double> addVat = num -> num + num / 5;
        System.out.println("Prices with VAT:");
        numbers.forEach(n -> System.out.printf("%1$.2f%n", addVat.apply(n)));
    }
}
