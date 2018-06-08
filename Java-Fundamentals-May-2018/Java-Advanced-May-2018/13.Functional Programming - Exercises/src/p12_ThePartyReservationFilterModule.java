import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.List;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p12_ThePartyReservationFilterModule {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> people = Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toList());
        HashSet<String> filter = new HashSet<>();
        readCommands(reader, filter);
        Predicate<String> allFilters = makeFilters(filter);
        printResult(people, allFilters);
    }

    private static void printResult(List<String> people, Predicate<String> allFilters) {
        people.stream().filter(allFilters).forEach(element -> System.out.print(element.concat(" ")));
    }

    private static Predicate<String> makeFilters(HashSet<String> filter) {
        return element -> {
                if (checkFilters(filter, element)){
                    return false;
                } else {
                    return true;
                }
            };
    }

    private static boolean checkFilters(HashSet<String> filter, String element) {
        for (String current : filter) {
            if (filter(current).test(element)) {
                return true;
            }
        }
        return false;
    }

    private static void readCommands(BufferedReader reader, HashSet<String> filter) throws IOException {
        String line = reader.readLine();
        while (!"Print".equals(line)) {
            String[] commands = line.split(";", 2);
            String command = commands[0];
            String type = commands[1];
            checkCommand(filter, command, type);
            line = reader.readLine();
        }
    }

    private static void checkCommand(HashSet<String> filter, String command, String type) {
        if ("Remove filter".equals(command)) {
            filter.remove(type);
        } else if ("Add filter".equals(command)) {
            filter.add(type);
        }
    }


    private static Predicate<String> filter(String currentFilter) {
        String[] elements = currentFilter.split(";");
        String type = elements[0];
        String value = elements[1];
        return checkType(type, value);
    }

    private static Predicate<String> checkType(String type, String value) {
        switch (type) {
            case "Ends with":
                return getEnd(value);
            case "Contains":
                return getContains(value);
            case "Starts with":
                return getStart(value);
            case "Length":
                return getLenght(value);
            default:
                return getFalse();
        }
    }

    private static Predicate<String> getFalse() {
        return x -> false;
    }

    private static Predicate<String> getStart(String value) {
        return x -> x.startsWith(value);
    }

    private static Predicate<String> getEnd(String value) {
        return x -> x.endsWith(value);
    }

    private static Predicate<String> getContains(String value) {
        return x -> x.contains(value);
    }

    private static Predicate<String> getLenght(String value) {
        return x -> x.length() == Integer.parseInt(value);
    }
}
