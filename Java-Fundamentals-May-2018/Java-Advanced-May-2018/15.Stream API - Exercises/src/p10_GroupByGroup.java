import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class p10_GroupByGroup {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, List<String>> people = new HashMap<>();
        readPeople(reader, people);

        printResult(people);
    }

    private static void printResult(HashMap<String, List<String>> people) {
        people.entrySet().forEach(x -> {
            System.out.print(x.getKey() + " - ");
            String names = String.format(String.join(", ", x.getValue()).replaceAll("[\\[\\]]", ""));
            System.out.printf("%s%n", names);
        });
    }

    private static void readPeople(BufferedReader reader, HashMap<String, List<String>> people) throws IOException {
        while (true){
            String line = reader.readLine();
            if ("END".equals(line)){
                break;
            }

            String[] tokens = line.split("\\s+");
            String name = tokens[0] + " " + tokens[1];
            String group = tokens[2];
            people.putIfAbsent(group, new ArrayList<>(0));
            people.get(group).add(name);
        }
    }
}
