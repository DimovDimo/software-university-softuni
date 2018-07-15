package C03_StackIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MyStack myStack = new MyStack();
        try {
            while (true){
                String line = reader.readLine();
                if ("END".equals(line)){
                    break;
                }

                String[] tokens = line.split("(|,)\\s+");
                switch (tokens[0]){
                    case "Push":
                        myStack.push(Arrays.stream(tokens)
                                .skip(1)
                                .toArray(String[]::new));
                        break;
                    case "Pop":
                        myStack.pop();
                        break;
                }
            }

            for (String element : myStack) {
                System.out.println(element);
            }
        } catch (IndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
        }
    }
}
