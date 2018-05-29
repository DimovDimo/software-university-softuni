import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class Problem01UniqueUsernames {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<String> uniqueUsernames = new LinkedHashSet<>();
        int countNames = Integer.parseInt(reader.readLine());
        for (int i = 0; i < countNames; i++) {
            uniqueUsernames.add(reader.readLine());
        }

        for (String uniqueUsername : uniqueUsernames) {
            System.out.println(uniqueUsername);
        }
    }
}
