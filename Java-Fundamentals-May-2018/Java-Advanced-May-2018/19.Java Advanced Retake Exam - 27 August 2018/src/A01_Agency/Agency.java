package A01_Agency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Agency {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayDeque<String> idsStack = new ArrayDeque<>();
        ArrayDeque<String> ahentsQueue = new ArrayDeque<>();

        String[] tokensIds = reader.readLine().split(" "); // Long
        for (String tokensId : tokensIds) {
            idsStack.push(tokensId);
        }

        String[] tokensAhents = reader.readLine().split(" "); // Long
        for (String tokensAhent : tokensAhents) {
            ahentsQueue.add(tokensAhent);
        }

        while (true){
            String line = reader.readLine();
            if ("stop".equals(line)){
                break;
            }
            
            String[] tokens = line.split(" ");
            
            switch (tokens[0]){
                case "add-ID":
                    idsStack.push(tokens[1]);
                    break;
                case "add-agent":
                    ahentsQueue.add(tokens[1]);
                    break;
                case "remove-ID":
                    System.out.println(
                            String.format("%s has been removed.", idsStack.remove()));;
                    break;
                case "remove-agent":
                    System.out.println(
                            String.format("%s has left the queue.", ahentsQueue.removeLast()));
                    break;
                case "sort-ID":
                    List<String> stack = idsStack.stream()
                            .sorted((id1, id2) -> id2.compareTo(id1))
                            .collect(Collectors.toList());
                    idsStack.clear();
                    stack.stream()
                            .forEach(e -> idsStack.add(e));
                    break;
            }
        }

        while (true){
            if (ahentsQueue.isEmpty()){
                if (ahentsQueue.size() != idsStack.size()){
                    System.out.println("No more agents left.");
                    while (idsStack.isEmpty() == false){
                        System.out.println(String.format("ID number: %s", idsStack.remove()));
                    }
                }

                break;
            }

            String agent = ahentsQueue.remove();

            try {
                String id = idsStack.pop();
                System.out.println(
                        String.format("%s takes ID number: %s", agent, id));
            } catch (Exception e){
                System.out.println(
                    String.format("%s does not have an ID.", agent));
            }
        }
    }
}
