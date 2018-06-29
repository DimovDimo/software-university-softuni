package p06_BirthdayCelebrations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Society> society = new ArrayList<>();

        fillSociety(reader, society);
        birthdayFilter(reader, society);
    }

    private static void birthdayFilter(BufferedReader reader, List<Society> society) throws IOException {
        String year = reader.readLine();
        for (Society person : society) {
            if (person.getBirthdate() == null){
                continue;
            } else if (person.isEndWithYear(year)){
                System.out.println(person.getBirthdate());
            }
        }
    }

    private static void fillSociety(BufferedReader reader, List<Society> society) throws IOException {
        while (true){
            String line = reader.readLine();
            if ("End".equals(line)){
                break;
            }

            String[] tokens = line.split("\\s+");
            switch (tokens[0]){
                case "Citizen":
                    String name = tokens[1];
                    Integer age = Integer.parseInt(tokens[2]);
                    String id = tokens[3];
                    String birthdate = tokens[4];
                    Society citizen = new Citizen(name, age, id, birthdate);
                    society.add(citizen);
                    break;
                case "Robot":
                    String model = tokens[1];
                    String idRobot = tokens[2];
                    Society robot = new Robot(model, idRobot);
                    society.add(robot);
                    break;
                case "Pet":
                    String namePet = tokens[1];
                    String birthdatePet = tokens[2];
                    Society pet = new Pet(namePet, birthdatePet);
                    society.add(pet);
            }
        }
    }
}
