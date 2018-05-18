import java.util.Scanner;

public class Problem07CharacterMultiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstWord = scanner.next();
        String secondWord = scanner.next();
        int sum = 0;
        int maxLenght = Math.max(firstWord.length(), secondWord.length());

        for (int i = 0; i < maxLenght; i++) {
            if (firstWord.length() > i && secondWord.length() > i){
                char firstChar = firstWord.charAt(i);
                char secondChar = secondWord.charAt(i);
                int firstCode = (int) firstChar;
                int secondCode = (int) secondChar;
                int multiplied = firstCode * secondCode;
                sum += multiplied;

            } else if (firstWord.length() > i){
                char firstChar = firstWord.charAt(i);
                int firstCode = (int) firstChar;
                sum += firstCode;
            } else if (secondWord.length() > i){
                char secondChar = secondWord.charAt(i);
                int secondCode = (int) secondChar;
                sum += secondCode;
            }
        }

        System.out.println(sum);
    }
}
