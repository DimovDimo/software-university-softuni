package p011_Threeuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Threeuple> threeuples = new ArrayList<>();
        readFirstThreeuple(reader, threeuples);
        readSecondThreeuple(reader, threeuples);
        readThirdThreeuple(reader, threeuples);
        printThreeuples(threeuples);
    }

    private static void printThreeuples(List<Threeuple> threeuples) {
        StringBuilder sb = new StringBuilder();
        for (Threeuple threeuple : threeuples) {
            sb.append(threeuple).append("\n");
        }

        System.out.print(sb.toString());
    }

    private static void readThirdThreeuple(BufferedReader reader, List<Threeuple> threeuples) throws IOException {
        String[] tokens = reader.readLine().split("\\s+");
        String name = tokens[0];
        double accountBalance = Double.parseDouble(tokens[1]);
        String bankName = tokens[2];
        Threeuple<String, Double, String> thirdThreeuple = new Threeuple<>(name, accountBalance, bankName);
        threeuples.add(thirdThreeuple);
    }

    private static void readSecondThreeuple(BufferedReader reader, List<Threeuple> threeuples) throws IOException {
        String[] tokens = reader.readLine().split("\\s+");
        String name = tokens[0];
        int litersOfBeer = Integer.parseInt(tokens[1]);
        boolean isDrunk = tokens[2].equals("drunk");
        Threeuple<String, Integer, Boolean> secondThreeuple = new Threeuple<>(name, litersOfBeer, isDrunk);
        threeuples.add(secondThreeuple);
    }

    private static void readFirstThreeuple(BufferedReader reader, List<Threeuple> threeuples) throws IOException {
        String[] tokens = reader.readLine().split("\\s+");
        String name = tokens[0] + " " + tokens[1];
        String address = tokens[2];
        String town = tokens[3];
        Threeuple<String, String, String> firstThreeuple = new Threeuple<>(name, address, town);
        threeuples.add(firstThreeuple);
    }
}
