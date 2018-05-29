import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem09MaximumElement {
    public static void main(String[] args) throws IOException {
        Deque<Integer> stack = new ArrayDeque<>(), maxStack = new ArrayDeque<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sstringBuilder = new StringBuilder();
        int num = Integer.parseInt(reader.readLine());
        int maxNumber = 0;
        for (int i = 0; i < num; i++) {
            String[] input = reader.readLine().split(" ");
            int command = Integer.parseInt(input[0]);
            if (command == 1) {
                maxNumber = getMaxNumberPush(stack, maxStack, maxNumber, input[1]);
            } else if (command == 2) {
                maxNumber = getMaxNumberDelete(stack, maxStack, maxNumber);
            } else {
                sstringBuilder.append(maxNumber).append(System.lineSeparator());
            }
        }

        printResult(sstringBuilder);
    }

    private static void printResult(StringBuilder sstringBuilder) {
        System.out.println(sstringBuilder);
    }

    private static int getMaxNumberDelete(Deque<Integer> stack, Deque<Integer> maxStack, int maxNumber) {
        if (stack.pop() == maxNumber) {
            maxStack.pop();
            if (maxStack.size() > 0) {
                maxNumber = maxStack.peek();
            } else {
                maxNumber = 0;
            }
        }
        return maxNumber;
    }

    private static int getMaxNumberPush(Deque<Integer> stack, Deque<Integer> maxStack, int maxNumber, String s) {
        int value = Integer.valueOf(s);
        if (maxNumber <= value) {
            maxNumber = value;
            maxStack.push(maxNumber);
        }
        stack.push(value);
        return maxNumber;
    }
}
