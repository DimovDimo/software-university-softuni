package p08_CustomListSorter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MyList<String> customList = new MyArrayList<>();
        while (true){
            String line = reader.readLine();
            if ("END".equals(line)){
                break;
            }

            String[] tokens = line.split("\\s+");

            String element;
            int firstIndex;
            int secondIndex;
            Object result = null;

            switch (tokens[0]){
                case "Add":
                    element = tokens[1];
                    customList.add(element);
                    break;
                case "Remove":
                    firstIndex = Integer.parseInt(tokens[1]);
                    customList.remove(firstIndex);
                    break;
                case "Contains":
                    element = tokens[1];
                    result = String.valueOf(customList.contains(element));
                    break;
                case "Swap":
                    firstIndex = Integer.parseInt(tokens[1]);
                    secondIndex = Integer.parseInt(tokens[2]);
                    customList.swap(firstIndex, secondIndex);
                    break;
                case "Greater":
                    element = tokens[1];
                    result = String.valueOf(customList.countGreaterThan(element));
                    break;
                case "Max":
                    result = customList.getMax();
                    break;
                case "Min":
                    result = customList.getMin();
                    break;
                case "Print":
                    result = customList.toString();
                    break;
                case "Sort":
                    customList.sort();
                    break;
            }

            if (result != null){
                System.out.println(
                        String.valueOf(result));
            }
        }
    }
}
