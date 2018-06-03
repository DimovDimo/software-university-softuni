import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem03TextFilter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] bannedWords = reader.readLine().split(", ");
        String textInput = reader.readLine();
        StringBuilder text = new StringBuilder();
        text.append(textInput);
        for (String bannedWord : bannedWords) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bannedWord.length(); i++) {
                sb.append("*");
            }


            int startIndex = 0;
            while (startIndex > -1){
                startIndex = text.indexOf(bannedWord);
                int endIndex = startIndex + bannedWord.length();
                if (startIndex > -1){
                    text.replace(startIndex, endIndex, sb.toString());
                }
            }
        }

        System.out.println(text);
    }
}
