import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class p02_UpperStrings {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Arrays.asList(reader.readLine().split("\\s+")).stream()
                .map(s -> s.toUpperCase())
                .forEach(s-> System.out.print(s + " "));

    }
}
