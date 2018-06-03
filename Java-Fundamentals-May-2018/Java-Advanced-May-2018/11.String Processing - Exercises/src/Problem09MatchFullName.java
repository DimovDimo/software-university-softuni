import java.util.Scanner;
import java.util.regex.Pattern;

public class Problem09MatchFullName {
    public static void main(String[] args) {
        String regex = "^[A-Z][a-z]+ [A-Z][a-z]+$";
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
