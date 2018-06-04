import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Problem06MagicExchangeableWords {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] words = reader.readLine().split("\\s+");
        String firstWord = words[0];
        String secondWord = words[1];
        long firstCount = firstWord
                .chars()
                .distinct()
                .count();
        long secondCount = secondWord
                .chars()
                .distinct()
                .count();
        boolean isExchange = firstCount == secondCount;
        System.out.println(isExchange);
    }
}
