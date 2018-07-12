package p10_Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Tuple> tuples = new ArrayList<>();
        readFirstTuple(reader, tuples);
        readSecondTuple(reader, tuples);
        readThirdTuple(reader, tuples);
        µprintTuples(tuples);
    }

    private static void µprintTuples(List<Tuple> tuples) {
        for (Tuple tuple : tuples) {
            System.out.println(tuple.toString());
        }
    }

    private static void readThirdTuple(BufferedReader reader, List<Tuple> tuples) throws IOException {
        String[] tokens = reader.readLine().split("\\s+");
        int firstParameter = Integer.parseInt(tokens[0]);
        double secondParameter = Double.parseDouble(tokens[1]);
        Tuple<Integer, Double> thirdTuple = new Tuple<>(firstParameter, secondParameter);
        tuples.add(thirdTuple);
    }

    private static void readSecondTuple(BufferedReader reader, List<Tuple> tuples) throws IOException {
        String[] tokens = reader.readLine().split("\\s+");
        String name = tokens[0];
        int litersOfBeer = Integer.parseInt(tokens[1]);
        Tuple<String, Integer> secondTuple = new Tuple<>(name, litersOfBeer);
        tuples.add(secondTuple);
    }

    private static void readFirstTuple(BufferedReader reader, List<Tuple> tuples) throws IOException {
        String[] tokens = reader.readLine().split("\\s+");
        String name = tokens[0] + " " + tokens[1];
        String address = tokens[2];
        Tuple<String, String> firstTuple = new Tuple<>(name, address);
        tuples.add(firstTuple);
    }
}
