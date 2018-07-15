package A01_ListyIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] createStrings = Arrays.stream(reader.readLine()
                .split("\\s+"))
                .skip(1)
                .toArray(String[]::new);
        ListyIterator listyIterator = new ListyIterator();
        listyIterator.Create(createStrings);
        try {
            while (true){
                String command = reader.readLine();
                if ("END".equals(command)){
                    break;
                }

                String result = null;
                switch (command){
                    case "Move":
                        result = String.valueOf(listyIterator.Move());
                        break;
                    case "Print":
                        listyIterator.Print();
                        break;
                    case "HasNext":
                        result = String.valueOf(listyIterator.HasNext());
                        break;
                }

                if (result != null){
                    System.out.println(result);
                }
            }
        } catch (IllegalStateException error){
            System.out.println(error.getMessage());
        }
    }
}
