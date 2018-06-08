import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class p11_PredicateParty {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> people = getPeople(reader);
        people = makeCommands(reader, people);
        printResult(people);
    }

    private static void printResult(List<String> people) {
        if (people.size() != 0) {
            System.out.printf("%s are going to the party!%n", String.join(", ", people));
        } else {
            System.out.printf("Nobody is going to the party!%n");
        }
    }

    private static List<String> makeCommands(BufferedReader reader, List<String> people) throws IOException {
        String line = reader.readLine();
        while (!"Party!".equals(line)) {
            String[] inputs = line.split("\\s+");
            String command = inputs[0];
            String type = inputs[1];
            String value = inputs[2];
            if ("Remove".equals(command)) {
                removePeople(people, type, value);
            } else if ("Double".equals(command)) {
                people = doublePeople(people, type, value);
            }

            line = reader.readLine();
        }

        return people;
    }

    private static void removePeople(List<String> people, String type, String value) {
        people.removeIf(getFilter(type, value));
    }

    private static List<String> doublePeople(List<String> people, String type, String value) {
        ArrayList<String> newPeople = new ArrayList<>();
        for (String name : people) {
            if (getFilter(type, value).test(name)) {
                newPeople.add(name);
            }

            newPeople.add(name);
        }

        people = newPeople;
        return people;
    }

    private static List<String> getPeople(BufferedReader reader) throws IOException {
        return Arrays
                .stream(reader
                        .readLine()
                        .split("\\s+"))
                .collect(Collectors
                        .toList());
    }

    private static Predicate<String> getFilter(String type, String value) {
        if ("StartsWith".equals(type)) {
            return getStart(value);
        } else if ("Length".equals(type)) {
            return getLenght(value);
        } else if ("EndsWith".equals(type)) {
            return getEnd(value);
        } else {
            return getFalse();
        }
    }

    private static Predicate<String> getFalse() {
        return x -> false;
    }

    private static Predicate<String> getLenght(String value) {
        return x -> x.length() == Integer.parseInt(value);
    }

    private static Predicate<String> getStart(String value) {
        return x -> x.startsWith(value);
    }

    private static Predicate<String> getEnd(String value) {
        return x -> x.endsWith(value);
    }
}
