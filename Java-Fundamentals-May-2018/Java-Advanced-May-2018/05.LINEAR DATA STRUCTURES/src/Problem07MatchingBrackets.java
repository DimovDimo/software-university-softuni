import java.util.ArrayDeque;
import java.util.Scanner;

public class Problem07MatchingBrackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String arithmeticalExpression = scanner.nextLine();
        ArrayDeque<Integer> stackBrackets = new ArrayDeque<>();
        for (int i = 0; i < arithmeticalExpression.length(); i++) {
            char currentChar = arithmeticalExpression.charAt(i);
            if (currentChar == '('){
                stackBrackets.push(i);
            } else if (currentChar == ')'){
                int startIndex = stackBrackets.pop();
                String contents = arithmeticalExpression.substring(startIndex, i +1);
                System.out.println(contents);
            }
        }
    }
}
