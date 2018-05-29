import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Problem04CountSymbols {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Map<String, Integer> countSymbols = new TreeMap<>();
        fillSimbols(line, countSymbols);
        printResult(countSymbols);
    }

    private static void fillSimbols(String line, Map<String, Integer> countSymbols) {
        for (int i = 0; i < line.length(); i++) {

            String current = String.valueOf(line.charAt(i));

            if(!countSymbols.containsKey(current)){
                countSymbols.put(current, 1);
            }else{
                int value = countSymbols.get(current) + 1;
                countSymbols.put(current, value);
            }
        }
    }

    private static void printResult(Map<String, Integer> countSymbols) {
        for (Map.Entry<String, Integer> currentSymbolEntry : countSymbols.entrySet()) {
            System.out.printf("%s: %d time/s%n", currentSymbolEntry.getKey(), currentSymbolEntry.getValue());
        }
    }
}
