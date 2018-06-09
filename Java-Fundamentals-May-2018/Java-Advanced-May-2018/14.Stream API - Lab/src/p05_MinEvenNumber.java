import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class p05_MinEvenNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OptionalDouble minNum = Arrays.stream(reader.readLine().split("\\s+"))
                .filter(x -> !x.isEmpty())
                .mapToDouble(Double::parseDouble)
                .filter(x -> x % 2 == 0)
                .min();
        if (minNum.isPresent()){
            System.out.printf("%.2f", minNum.getAsDouble());
        } else {
            System.out.println("No match");
        }
    }
}
