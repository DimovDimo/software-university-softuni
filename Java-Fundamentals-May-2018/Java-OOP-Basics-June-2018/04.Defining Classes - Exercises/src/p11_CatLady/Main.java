package p11_CatLady;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Cat> cats = getCats(reader);
        printResult(reader, cats);
    }

    private static void printResult(BufferedReader reader, List<Cat> cats) throws IOException {
        String name = reader.readLine();
        for (Cat cat : cats) {
            if (cat.getName().equals(name)){
                System.out.printf("%s%n", cat.toString());
            }
        }
    }

    private static List<Cat> getCats(BufferedReader reader) throws IOException {
        List<Cat> cats = new LinkedList<>();
        while (true){
            String line = reader.readLine();
            if ("End".equals(line)){
                break;
            }

            String[] tokens = line.split("\\s+");
            String breed = tokens[0];
            String name = tokens[1];
            double size = Double.parseDouble(tokens[2]);
            Cat cat = new Cat(breed, name, size);
            cats.add(cat);
        }
        return cats;
    }
}
