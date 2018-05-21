import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class Problem07ReverseNumbersWithAStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (String item : input) {
            stack.push(Integer.parseInt(item));
        }

        while (!stack.isEmpty()){
            System.out.printf("%s ", stack.pop());
        }
    }
}
