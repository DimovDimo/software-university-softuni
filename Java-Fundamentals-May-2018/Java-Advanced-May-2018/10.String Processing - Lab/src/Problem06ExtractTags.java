import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem06ExtractTags {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String line = scanner.nextLine();
            if ("END".equals(line)){
                break;
            }

            Pattern pattern = Pattern.compile("<.*?>");
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()){
                System.out.println(matcher.group());
            }
        }
    }
}
