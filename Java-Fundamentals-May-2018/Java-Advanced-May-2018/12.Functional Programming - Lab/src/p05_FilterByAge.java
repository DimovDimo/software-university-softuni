import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiPredicate;

public class p05_FilterByAge {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> people = new LinkedHashMap<>();
        fillPeople(reader, people);
        String old = reader.readLine();
        int age = Integer.parseInt(reader.readLine());
        Map<String, Integer> peopleFirtered = new LinkedHashMap<>();
        fillPeopleFirtered(people, old, age, peopleFirtered);
        printResult(reader, peopleFirtered);
    }

    private static void printResult(BufferedReader reader, Map<String, Integer> peopleFirtered) throws IOException {
        String printFormat = reader.readLine();
        if ("name".equals(printFormat)){
            for (Map.Entry<String, Integer> person : peopleFirtered.entrySet()) {
                System.out.println(person.getKey());
            }
        } else if ("age".equals(printFormat)){
            for (Map.Entry<String, Integer> person : peopleFirtered.entrySet()) {
                System.out.println(person.getValue());
            }
        } else if ("name age".equals(printFormat)){
            for (Map.Entry<String, Integer> person : peopleFirtered.entrySet()) {
                System.out.printf("%s - %d%n", person.getKey(), person.getValue());
            }
        }
    }

    private static void fillPeopleFirtered(Map<String, Integer> people, String old, int age, Map<String, Integer> peopleFirtered) {
        if ("younger".equals(old)){
            fillYounger(people, age, peopleFirtered);
        } else if("older".equals(old)){
            fillOlder(people, age, peopleFirtered);
        }
    }

    private static void fillOlder(Map<String, Integer> people, int age, Map<String, Integer> peopleFirtered) {
        for (Map.Entry<String, Integer> person : people.entrySet()) {
            if (person.getValue() >= age){
                peopleFirtered.put(person.getKey(), person.getValue());
            }
        }
    }

    private static void fillYounger(Map<String, Integer> people, int age, Map<String, Integer> peopleFirtered) {
        for (Map.Entry<String, Integer> person : people.entrySet()) {
            if (person.getValue() <= age){
                peopleFirtered.put(person.getKey(), person.getValue());
            }
        }
    }

    private static void fillPeople(BufferedReader reader, Map<String, Integer> people) throws IOException {
        int countPeople = Integer.parseInt(reader.readLine());
        for (int i = 0; i < countPeople; i++) {
            String[] inputs = reader.readLine().split(", ");
            String name = inputs[0];
            int age = Integer.parseInt(inputs[1]);
            people.put(name, age);
        }
    }
}
