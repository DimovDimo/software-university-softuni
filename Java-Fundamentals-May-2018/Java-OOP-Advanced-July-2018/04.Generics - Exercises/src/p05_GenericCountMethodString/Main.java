package p05_GenericCountMethodString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Box<String>> boxes = new ArrayList<>();
        int count = Integer.parseInt(reader.readLine());
        for (int i = 0; i < count; i++) {
            Box<String> box = new Box<>();
            box.setParameter(reader.readLine());
            boxes.add(box);
        }

        Box<String> box = new Box<>();
        box.setParameter(reader.readLine());

        System.out.println(Box.getCountGreaterElements(boxes, box));
    }
}
