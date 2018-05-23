import java.util.ArrayDeque;
import java.util.Scanner;

public class Problem10BasicQueueOperations {
    public static void main(String[] args) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        int countNumsForPush = Integer.parseInt(input[0]);
        int countNumsForPop = Integer.parseInt(input[1]);
        int checkNumber = Integer.parseInt(input[2]);
        String[] numbers = scanner.nextLine().split("\\s+");
        for (int i = 0; i < countNumsForPush; i++) {
            queue.add(Integer.parseInt(numbers[i]));
        }

        for (int i = 0; i < countNumsForPop; i++) {
            queue.poll();
        }

        if (queue.isEmpty()){
            System.out.printf("0");
        } else if (queue.contains(checkNumber)){
            System.out.printf("true");
        } else {
            printMinElementFromQueue(queue);
        }
    }

    private static void printMinElementFromQueue(ArrayDeque<Integer> queue) {
        int minElement = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            int currentElement = queue.poll();
            if (currentElement < minElement){
                minElement =currentElement;
            }
        }

        System.out.println(minElement);
    }
}

