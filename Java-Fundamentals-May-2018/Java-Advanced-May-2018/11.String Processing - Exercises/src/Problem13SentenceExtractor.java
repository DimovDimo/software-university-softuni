import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem13SentenceExtractor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String keyword = scanner.nextLine();
        String sentences = scanner.nextLine();
        Pattern pattern = Pattern.compile(".*?[.?!]");
        Matcher matcher = pattern.matcher(sentences);
        while (matcher.find()){
            String sentence = matcher.group();
            if (sentence.matches(".*?\\b" + keyword + "\\b.*")) {
                System.out.println(sentence.trim());
            }
        }
    }
}
