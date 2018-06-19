package p03_AnimalFarm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine();
            int age = Integer.parseInt(reader.readLine());
            Chicken chicken = new Chicken(name, age);
            System.out.printf("Chicken %s (age %d) can produce %s eggs per day.", chicken.getName(), chicken.getAge(), chicken.productPerDay());
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }
}
