import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem04CountSameValuesInArray {
    public static void main(String[] args) {
        Map<String, Integer> resilt = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        for (String number : input) {
            if (!resilt.containsKey(number)){
                resilt.put(number, 1);
            } else {
                resilt.put(number, resilt.get(number) + 1);
            }
        }

        for (String key : resilt.keySet()) {
            System.out.printf("%s - %d times%n", key, resilt.get(key));
        }
    }
}
