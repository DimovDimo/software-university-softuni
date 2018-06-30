package p09_MooD3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = reader.readLine().split(" \\| ");
        String name = tokens[0];
        String type = tokens[1];
        String point = tokens[2];
        String level = tokens[3];

        if ("Demon".equals(type)){
            Passwordable demon = new Demon(name,type, point, level);
            demon.hashPassword();
            System.out.println(demon.toString());

        } else {
            Passwordable archangel = new Archangel(name,type, point, level);
            archangel.hashPassword();
            System.out.println(archangel.toString());
        }
    }
}
