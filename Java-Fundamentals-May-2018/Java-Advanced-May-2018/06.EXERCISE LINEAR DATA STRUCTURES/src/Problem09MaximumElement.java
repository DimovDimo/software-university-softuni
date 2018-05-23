import java.util.*;

public class Problem09MaximumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        List<String> maxElements = new ArrayList<>();
        int countCommands = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < countCommands; i++) {
            String[] command = scanner.nextLine().split("\\s+");
            switch (command[0]){
                case "1":
                    pushStringAsNumber(stack, command[1]);
                    break;
                case "2":
                    stack.pop();
                    break;
                case "3":
                    maxElements.add(getMaxElement(stack).toString());
                    break;
                default:
                    break;
            }
        }

        System.out.printf("%s", String.join(" ", maxElements));
    }

    private static Integer getMaxElement(ArrayDeque<Integer> stack) {
        int maxElement = Integer.MIN_VALUE;
        for (Integer currentNum : stack) {
            if (currentNum > maxElement){
                maxElement = currentNum;
            }
        }

        return  maxElement;
    }

    private static void pushStringAsNumber(ArrayDeque<Integer> stack, String s) {
        int numberForPush = Integer.parseInt(s);
        stack.push(Integer.parseInt(s));
    }
}
