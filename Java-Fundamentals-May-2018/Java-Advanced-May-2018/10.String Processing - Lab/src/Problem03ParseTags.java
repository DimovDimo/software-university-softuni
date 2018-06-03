import java.util.Scanner;

public class Problem03ParseTags {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] text = scanner.nextLine().split("<upcase>|<\\/upcase>");
        for (int i = 1; i < text.length; i+=2) {
            text[i] = text[i].toUpperCase();
        }

        System.out.println(String.join("", text));
    }
}
