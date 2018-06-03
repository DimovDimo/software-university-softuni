import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Poblem01CountSubstringOccurrences {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine().toLowerCase();
        String givenString = reader.readLine().toLowerCase();

        int countAppearsInAGivenText = 0;
        int startIndex = 0;
        while (startIndex >= 0){
            int currentIndex = text.indexOf(givenString, startIndex);
            if (currentIndex >= 0){
                countAppearsInAGivenText++;
                startIndex = currentIndex + 1;
            } else {
                startIndex = currentIndex;
            }
        }

        System.out.println(countAppearsInAGivenText);
    }
}
