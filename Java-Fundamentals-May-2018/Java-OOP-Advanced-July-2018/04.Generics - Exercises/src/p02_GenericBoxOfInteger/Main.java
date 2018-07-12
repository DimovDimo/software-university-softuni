package p02_GenericBoxOfInteger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Box> boxes = new ArrayList<>();
        int count = Integer.parseInt(reader.readLine());
        for (int i = 0; i < count; i++) {
            Integer number = Integer.parseInt(reader.readLine());
            boxes.add(new Box(number));
        }

        for (Box box : boxes) {
            System.out.println(box.toString());
        }
    }
}
