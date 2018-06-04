import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem12ExtractEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Pattern pattern = Pattern.compile("(^|\\s)[\\dA-Za-z][\\w\\-.]+?[\\dA-Za-z]@[A-Za-z][A-Za-z-.]*?(\\.[A-Za-z-.]*?[A-Za-z]+)+");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()){
            System.out.println(matcher.group().trim());
        }
    }
}
