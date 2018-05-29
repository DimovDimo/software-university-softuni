import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Problem15InfixToPostfix {
    public static void main(String[] args) throws IOException {
        ArrayDeque<String> stack = new ArrayDeque<>();
        ArrayDeque<String> queue = new ArrayDeque<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = getElements(reader);
        preparationElements(stack, queue, elements);
        fiillQueue(stack, queue);
        printResult(queue);
    }

    private static void printResult(ArrayDeque<String> queue) {
        System.out.println(String.join(" ", queue));
    }

    private static void fiillQueue(ArrayDeque<String> stack, ArrayDeque<String> queue) {
        while (!stack.isEmpty()) {
            queue.offer(stack.pop());
        }
    }

    private static void preparationElements(ArrayDeque<String> stack, ArrayDeque<String> queue, String[] elements) {
        for (String element : elements) {
            if (!"-+/*()".contains(element)) {
                queue.offer(element);
            } else if (element.equals("(")) {
                stack.push(element);
            } else if (element.equals(")")) {
                queueOffer(stack, queue);
                stack.pop();
            } else {
                checkPlusOrMinus(stack, queue, element);
                stack.push(element);
            }
        }
    }

    private static void checkPlusOrMinus(ArrayDeque<String> stack, ArrayDeque<String> queue, String element) {
        if ("-+".contains(element)) {
            while (!stack.isEmpty() && !stack.peek().equals("(")) {
                queue.offer(stack.pop());
            }
        } else {
            while (stack.isEmpty() == false && "+-(".contains(stack.peek()) == false) {
                queue.offer(stack.pop());
            }
        }
    }

    private static void queueOffer(ArrayDeque<String> stack, ArrayDeque<String> queue) {
        while (stack.peek().equals("(") == false) {
            queue.offer(stack.pop());
        }
    }

    private static String[] getElements(BufferedReader reader) throws IOException {
        String input = reader.readLine();
        return input.split("\\s+");
    }
}
