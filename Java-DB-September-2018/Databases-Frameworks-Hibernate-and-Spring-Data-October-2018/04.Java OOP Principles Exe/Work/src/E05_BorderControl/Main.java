package E05_BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Detainable> detainables = new ArrayList<>();

        while (true){
            String line = scanner.nextLine();
            if ("End".equals(line)){
                break;
            }

            String[] tokens = line.split(" ");
            if (tokens.length == 3){
                detainables.add(new Citizen(tokens[0],tokens[1],tokens[2]));
            } else if (tokens.length == 2){
                detainables.add(new Robot(tokens[0],tokens[1]));
            }
        }

        String target = scanner.nextLine();
        for (Detainable detainable : detainables) {
            detainable.dataine(target);
        }
    }
}
