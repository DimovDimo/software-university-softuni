import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class p02_StudentsByFirstAndLastName {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, List<Pair<String, String>>> students = new HashMap<>();
        while (true){
            String line = reader.readLine();
            if ("END".equals(line)){
                break;
            }

            String[] student = line.split("\\s+");
            String firstName = student[0];
            String lastName = student[1];
            String group = "2";

            Pair<String, String> name = new Pair<>(firstName, lastName);
            List<Pair<String, String>> names = new ArrayList<>();
            if (students.containsKey(group)){
                names =students.get(group);
            }

            names.add(name);
            students.putIfAbsent(group, names);
        }

        students.entrySet().stream()
                .filter(g -> g.getKey().equals("2"))
                .forEach(g -> g.getValue().stream()
                        .filter(s -> s.getValue().compareTo(s.getKey()) > 0)
                        .forEach(s -> System.out.printf("%s %s%n", s.getKey(), s.getValue())));
    }
}
