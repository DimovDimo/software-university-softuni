package p03_OpinionPoll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int countPeople = Integer.parseInt(reader.readLine());
        List<Person> people = new LinkedList<>();
        for (int i = 0; i < countPeople; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            Person person = new Person(name, age);
            people.add(person);
        }

        people.stream()
                .filter(p -> p.getAge() > 30)
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .forEach(p -> System.out.printf("%s - %d%n", p.getName(), p.getAge()));
    }
}
