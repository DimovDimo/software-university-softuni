import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class p03_CountUppercaseWords {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Predicate<String> isStartWithUppercase = s -> {
            return s.charAt(0) == s.toUpperCase().charAt(0);
        };

        String[] words = reader.readLine().split("\\s+");
        List<String> uppercaseWords = new ArrayList<>();
        Collections.addAll(uppercaseWords, words);
        uppercaseWords.removeIf(w -> isStartWithUppercase.test(w) == false);
        System.out.println(uppercaseWords.size());
        uppercaseWords.forEach(w -> System.out.println(w));
    }
}
