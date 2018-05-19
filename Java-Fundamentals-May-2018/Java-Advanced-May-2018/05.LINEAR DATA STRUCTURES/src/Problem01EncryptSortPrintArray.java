import java.util.Arrays;
import java.util.Scanner;

public class Problem01EncryptSortPrintArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countNames = Integer.parseInt(scanner.nextLine());
        String[] names = new String[countNames];

        for (int i = 0; i < names.length; i++) {
            names[i] = scanner.nextLine();
        }

        int[] encryptedNames = new int[countNames];

        for (int j = 0; j < names.length; j++) {
            int currentNmesSum = 0;
            String name = names[j];
            for (int i = 0; i < name.length(); i++) {
                char currentLetter = name.charAt(i);
                if (checkLetterIsVowel(currentLetter)){
                    currentNmesSum += currentLetter * name.length();
                } else {
                    currentNmesSum += currentLetter / name.length();
                }
            }

            encryptedNames[j] = currentNmesSum;
        }

        Arrays.sort(encryptedNames);
        printResult(encryptedNames);
    }

    public static boolean checkLetterIsVowel (char letter) {
        char[] vowels = new char[] {'a','e','i','o','u'};

        for (char vowel : vowels) {
            if (vowel == Character.toLowerCase(letter)){
                return true;
            }
        }

        return false;
    }

    public static void printResult (int[] values){
        for (int value : values) {
            System.out.println(value);
        }
    }
}
