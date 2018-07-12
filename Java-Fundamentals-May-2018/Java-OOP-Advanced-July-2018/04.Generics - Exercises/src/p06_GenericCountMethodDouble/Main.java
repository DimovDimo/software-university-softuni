package p06_GenericCountMethodDouble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Box<Double>> boxes = new ArrayList<>();
        int count = Integer.parseInt(reader.readLine());
        for (int i = 0; i < count; i++) {
            Box<Double> box = new Box<>();
            box.setParameter(Double.parseDouble(reader.readLine()));
            boxes.add(box);
        }

        Box<Double> box = new Box<>();
        box.setParameter(Double.parseDouble(reader.readLine()));

        System.out.println(Box.getCountGreaterElements(boxes, box));
    }
}
