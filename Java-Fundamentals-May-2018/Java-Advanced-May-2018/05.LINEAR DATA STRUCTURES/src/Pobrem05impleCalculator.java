import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Pobrem05impleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        ArrayDeque<String> stack = new ArrayDeque<>();
        Collections.addAll(stack, input);
        while (stack.size() > 1){
            int firstNum = Integer.parseInt(stack.pop());
            String operand = stack.pop();
            int secondNum = Integer.parseInt(stack.pop());

            if (operand.equals("+")){
                stack.push(firstNum + secondNum +"");
            } else {
                stack.push(firstNum - secondNum +"");
            }
        }

        System.out.print(stack.peek());
    }
}
