package E05_ComparingObjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> people = new ArrayList<>();
        while (true){
            String line = reader.readLine();
            if ("END".equals(line)){
                break;
            }

            String[] tokens = line.split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            String town = tokens[2];
            Person person = new Person(name, age, town);
            people.add(person);
        }

        try {
            int personIndex = Integer.parseInt(reader.readLine());
            Person person = people.get(personIndex);
            int numberOfEqualPeople = 0;
            int numberOfNotEqualPeople = 0;
            int totalNumberOfPeople = people.size();
            for (Person currentPerson : people) {
                if (person.compareTo(currentPerson) == 0){
                    numberOfEqualPeople++;
                } else {
                    numberOfNotEqualPeople++;
                }
            }

            System.out.printf("%d %d %d",
                    numberOfEqualPeople,
                    numberOfNotEqualPeople,
                    totalNumberOfPeople);

        } catch (IndexOutOfBoundsException error){
            System.out.println("No matches");
        }
    }
}
