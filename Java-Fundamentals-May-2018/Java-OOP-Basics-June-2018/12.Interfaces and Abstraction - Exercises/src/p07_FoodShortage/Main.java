package p07_FoodShortage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Society> society = new HashMap<>();

        fillSociety(reader, society);
        buyFood(reader, society);
        printTotalFood(society);
    }

    private static void printTotalFood(Map<String, Society> society) {
        int totalFood = 0;
        for (Society person : society.values()) {
            totalFood += person.food;
        }

        System.out.println(totalFood);
    }

    private static void buyFood(BufferedReader reader, Map<String, Society> society) throws IOException {
        while (true){
            String name = reader.readLine();
            if ("End".equals(name)){
                break;
            }

            if (society.containsKey(name)){
                society.get(name).buyFood();
            }
        }
    }

    private static void fillSociety(BufferedReader reader, Map<String, Society> society) throws IOException {
        int peopleCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < peopleCount; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            if (isCitizen(tokens)){
                String name = tokens[0];
                Integer age = Integer.parseInt(tokens[1]);
                String id = tokens[2];
                String birthdate = tokens[3];
                Society citizen = new Citizen(name, age, id, birthdate);
                society.put(name, citizen);
            } else {
                String name = tokens[0];
                Integer age = Integer.parseInt(tokens[1]);
                String group = tokens[2];
                Society rebel = new Rebel(name, age, group);
                society.put(name, rebel);
            }
        }
    }

    private static boolean isCitizen(String[] tokens) {
        if (tokens.length == 4){
            return true;
        } else {
            return false;
        }
    }
}
