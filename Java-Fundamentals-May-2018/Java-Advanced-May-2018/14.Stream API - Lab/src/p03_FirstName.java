import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class p03_FirstName {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> names = Arrays.asList(reader.readLine().split("\\s+")).stream()
                .sorted()
                .collect(Collectors.toList());
        List<String> letters = Arrays.asList(reader.readLine().split("\\s+"));
        Optional<String> firstName = names.stream()
                .filter(x -> {
                    for (String letter : letters) {
                        if ((x.charAt(0) + "").equalsIgnoreCase(letter)){
                            return true;
                        }
                    }

                    return false;
                }).findFirst();
        if (firstName.isPresent()){
            System.out.println(firstName.get());
        } else {
            System.out.println("No match");
        }
    }
}
