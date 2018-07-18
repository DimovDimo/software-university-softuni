package C03_CoffeeMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        while (true){
            String line = reader.readLine();
            if ("END".equals(line)){
                break;
            }

            String[] tokens = line.split("\\s+");
            switch (tokens.length){
                case 1:
                    coffeeMachine.insertCoin(tokens[0]);
                    break;
                case 2:
                    String size = tokens[0];
                    String type = tokens[1];
                    coffeeMachine.buyCoffee(size, type);
                    break;
            }
        }

        for (Coffee coffee : coffeeMachine.coffeesSold()) {
            System.out.println(coffee);
        }
    }
}
