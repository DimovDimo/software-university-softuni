import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem02SplitByWordCasing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        String[] words = text.split("[\\\\,;:.!()\"'/\\[\\] ]");

        List<String> loweerCaseWords = new ArrayList<>();
        List<String> upperCaseWords = new ArrayList<>();
        List<String> mixedCaseWords = new ArrayList<>();

        for (String word : words) {
            if (word.trim().length() == 0){
                continue;
            }

            boolean isSpecial = false;
            for (int i = 0; i < word.length(); i++) {
                String currentChar = "" + word.charAt(i);
                if (currentChar.toUpperCase().equals(currentChar) &&
                        currentChar.toLowerCase().equals(currentChar)){
                    isSpecial = true;
                    break;
                }
            }

            if (isSpecial){
                mixedCaseWords.add(word);
            } else if (word.equals(word.toLowerCase())){
                loweerCaseWords.add(word);
            } else if (word.equals(word.toUpperCase())){
                upperCaseWords.add(word);
            } else {
                mixedCaseWords.add(word);
            }
        }

        System.out.printf("Lower-case: %s%n", String.join(", ", loweerCaseWords));
        System.out.printf("Mixed-case: %s%n", String.join(", ", mixedCaseWords));
        System.out.printf("Upper-case: %s", String.join(", ", upperCaseWords));
    }
}
