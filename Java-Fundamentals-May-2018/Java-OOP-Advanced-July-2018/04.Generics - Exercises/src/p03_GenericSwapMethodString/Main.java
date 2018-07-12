package p03_GenericSwapMethodString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        Box[] boxes = new Box[count];
        for (int i = 0; i < count; i++) {
            String element = reader.readLine();
            boxes[i] = new Box(element);
        }

        int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::valueOf)
                .toArray();
        int firstIndex = tokens[0];
        int secondIndex = tokens[1];

        Box temporalBox = new Box(
                boxes[firstIndex]
                .getParameter());
        boxes[firstIndex] = new Box(
                boxes[secondIndex]
                        .getParameter());
        boxes[secondIndex] = temporalBox;

        for (Box box : boxes) {
            System.out.println(box.toString());
        }
    }
}
