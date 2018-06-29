package p05_BorderControl;

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
        borderFilter(reader, society);
    }

    private static void borderFilter(BufferedReader reader, List<Society> society) throws IOException {
        String endString = reader.readLine();
        for (Society person : society) {
            if (person.isEndWith(endString)){
                System.out.println(person.getId());
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
            if (isCitizen(tokens)){
                String name = tokens[0];
                Integer age = Integer.parseInt(tokens[1]);
                String id = tokens[2];
                Society citizen = new Citizen(name, age, id);
                society.add(citizen);

            } else {
                String model = tokens[0];
                String id = tokens[1];
                Society robot = new Robot(model, id);
                society.add(robot);
            }
        }
    }

    private static boolean isCitizen(String[] tokens) {
        if (tokens.length == 3){
            return true;
        } else {
            return false;
        }
    }
}
