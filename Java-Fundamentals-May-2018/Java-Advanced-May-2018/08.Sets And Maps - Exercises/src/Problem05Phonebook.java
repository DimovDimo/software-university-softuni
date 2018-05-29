import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem05Phonebook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> phonebook = new HashMap<>();
        fillPhonebook(scanner, phonebook);
        searchInPhonebook(scanner, phonebook);
    }

    private static void searchInPhonebook(Scanner scanner, Map<String, String> phonebook) {
        while (true) {
            String name = scanner.nextLine();
            if ("stop".equals(name)){
                break;
            } else if (phonebook.containsKey(name)){
                System.out.printf("%s -> %s%n", name, phonebook.get(name));
            } else {
                System.out.printf("Contact %s does not exist.%n", name);
            }
        }
    }

    private static void fillPhonebook(Scanner scanner, Map<String, String> phonebook) {
        while (true) {
            String line = scanner.nextLine();
            if ("search".equals(line)){
                break;
            } else {
                String[] inputs = line.split("-");
                phonebook.put(inputs[0], inputs[1]);
            }
        }
    }
}
