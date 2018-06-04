import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem10MatchPhoneNumber {
    public static void main(String[] args) {
        String regex = "^\\+359([ -])2\\1\\d{3}\\1\\d{4}$";
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!line.equals("end")){
            if (Pattern.matches(regex, line)){
                System.out.println(line);
            }

            line = scanner.nextLine();
        }
    }
}
