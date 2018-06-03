import java.util.Scanner;

public class Problem04UnicodeCharacters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        sb.append(scanner.nextLine());
        for (int i = 0; i < sb.length(); i++) {
            System.out.printf("\\u%04x", (int) sb.charAt(i));
        }
    }
}
