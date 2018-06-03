import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Problem05Palindromes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split("[ ,.?!]+");
        Set<String> palindromes = new TreeSet<>();
        for (String word : words) {
            if (word != null){
                StringBuilder reverser = new StringBuilder();
                reverser.append(word).reverse();
                if (word.equals(reverser.toString())){
                    palindromes.add(word);
                }
            }
        }

        System.out.println(palindromes);
    }
}
