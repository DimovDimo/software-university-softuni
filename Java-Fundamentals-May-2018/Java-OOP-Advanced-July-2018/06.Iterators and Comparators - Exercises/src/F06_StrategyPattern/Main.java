package F06_StrategyPattern;

import F06_StrategyPattern.Comparators.ComparatorAge;
import F06_StrategyPattern.Comparators.ComparatorLength;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<Person> treeSetByName = new TreeSet<>(new ComparatorLength());
        Set<Person> treeSetByAge = new TreeSet<>(new ComparatorAge());
        int countPersons = Integer.parseInt(reader.readLine());
        for (int i = 0; i < countPersons; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            Person person = new Person(name, age);
            treeSetByName.add(person);
            treeSetByAge.add(person);
        }

        for (Person person : treeSetByName) {
            System.out.println(person.toString());
        }

        for (Person person : treeSetByAge) {
            System.out.println(person.toString());
        }
    }
}
