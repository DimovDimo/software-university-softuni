import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem08BasicStackOperations {
    public static void main(String[] args) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        int countNumsForPush = Integer.parseInt(input[0]);
        int countNumsForPop = Integer.parseInt(input[1]);
        int checkNumber = Integer.parseInt(input[2]);
        String[] numbers = scanner.nextLine().split("\\s+");
        for (int i = 0; i < countNumsForPush; i++) {
            stack.push(Integer.parseInt(numbers[i]));
        }

        for (int i = 0; i < countNumsForPop; i++) {
            stack.pop();
        }

        if (stack.isEmpty()){
            System.out.printf("0");
        } else if (stack.contains(checkNumber)){
            System.out.printf("true");
        } else {
            printMinElementFromStack(stack);
        }
    }

    private static void printMinElementFromStack(ArrayDeque<Integer> stack) {
        int minElement = Integer.MAX_VALUE;
        while (!stack.isEmpty()){
            int currentElement = stack.pop();
            if (currentElement < minElement){
                minElement =currentElement;
            }
        }

        System.out.println(minElement);
    }
}
