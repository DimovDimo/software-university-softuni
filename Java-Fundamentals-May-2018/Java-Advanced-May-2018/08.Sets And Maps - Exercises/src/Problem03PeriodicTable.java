import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Problem03PeriodicTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> periodicTable = new TreeSet<>();
        int countInputs = Integer.parseInt(scanner.nextLine());
        fillPeriodicTable(scanner, periodicTable, countInputs);
        printAllUniqueOnes(periodicTable);
    }

    private static void printAllUniqueOnes(Set<String> periodicTable) {
        for (String element : periodicTable) {
            System.out.print(element + " ");
        }
    }

    private static void fillPeriodicTable(Scanner scanner, Set<String> periodicTable, int countInputs) {
        for (int i = 0; i < countInputs; i++) {
            String[] elements = scanner.nextLine().split("\\s+");
            addElements(periodicTable, elements);
        }
    }

    private static void addElements(Set<String> periodicTable, String[] elements) {
        for (String element : elements) {
            periodicTable.add(element);
        }
    }
}
