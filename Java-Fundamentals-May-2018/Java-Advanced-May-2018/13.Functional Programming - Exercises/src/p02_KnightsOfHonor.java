import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class p02_KnightsOfHonor {
    public static void main(String[] args) throws IOException {
        Consumer<List<String>> printSrings = list -> {
            list.forEach(element -> System.out.printf("Sir %s%n", element));
        };

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split("\\s+");
        List<String> words = new LinkedList<>();
        Collections.addAll(words, inputs);
        printSrings.accept(words);
    }
}
