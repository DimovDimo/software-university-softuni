import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class p13_InfernoIII {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] elements = getElements(reader);
        Set<String> filters = makeCommands(reader);
        Predicate<Integer> allFilters = checkAllFilters(elements, filters);
        printResult(elements, allFilters);
    }

    private static void printResult(int[] elements, Predicate<Integer> allFilters) {
        for (int i = 0; i < elements.length; i++){
            if (allFilters.test(i)) {
                System.out.print(elements[i] + " ");
            }
        }
    }

    private static Predicate<Integer> checkAllFilters(int[] elements, Set<String> filters) {
        return x -> {
                if (checkFilters(elements, filters, x)){
                    return false;
                } else {
                    return true;
                }
            };
    }

    private static boolean checkFilters(int[] elements, Set<String> filters, Integer x) {
        for (String currentFilter : filters) {
            if (generateFilter(currentFilter, elements).test(x)) {
                return true;
            }
        }
        return false;
    }

    private static Set<String> makeCommands(BufferedReader reader) throws IOException {
        Set<String> filters = new HashSet<>();
        String line = reader.readLine();
        while (!"Forge".equals(line)) {
            String[] commands = line.split(";", 2);
            String command = commands[0];
            String filter = commands[1];
            checkCommand(filters, command, filter);
            line = reader.readLine();
        }

        return filters;
    }

    private static void checkCommand(Set<String> filters, String command, String filter) {
        if ("Reverse".equals(command)){
            filters.remove(filter);
        } else if ("Exclude".equals(command)) {
            filters.add(filter);
        }
    }

    private static int[] getElements(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }

    private static Predicate<Integer> generateFilter(String filter, int[] items) {
        String[] elements = filter.split(";");
        String type = elements[0];
        int value = Integer.parseInt(elements[1]);
        return getElement(items, type, value);
    }

    private static Predicate<Integer> getElement(int[] items, String type, int value) {
        return x -> {
            int right = getRight(items, x);
            int left = getLeft(items, x);
            return checkType(items, type, value, x, right, left);
        };
    }

    private static boolean checkType(int[] items, String type, int value, Integer x, int right, int left) {
        switch (type) {
            case "Sum Left Right":
                return left + right + items[x] == value;
            case "Sum Right":
                return  right + items[x] == value;
            case "Sum Left":
                return left + items[x] == value;
            default:
                return false;
        }
    }

    private static int getLeft(int[] items, Integer x) {
        if (x != 0){
            return items[x - 1];
        } else {
            return 0;
        }
    }

    private static int getRight(int[] items, Integer x) {
        if (x != items.length - 1){
            return items[x + 1];
        } else {
            return 0;
        }
    }
}
