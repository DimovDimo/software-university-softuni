import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem12BalancedParentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayDeque<String> stack = new ArrayDeque<>();
        if (input.length() % 2 != 0 && input.length() != 0){
            System.out.printf("NO");
        } else {
            boolean isBalansed = true;
            int countIteration = input.length();
            for (int i = 0; i < countIteration; i++) {
                if (isBalansed == false){
                    break;
                }

                String currentCharAsString = input.charAt(i) + "";
                if (currentCharAsString.equals("{") || currentCharAsString.equals("[") || currentCharAsString.equals("(")){
                    stack.push(currentCharAsString);
                } else if (currentCharAsString.equals("}") || currentCharAsString.equals("]") || currentCharAsString.equals(")")) {
                    String lastOpen = stack.pop();
                    switch (lastOpen){
                        case "{":
                            isBalansed = currentCharAsString.equals("}");
                            break;
                        case "[":
                            isBalansed = currentCharAsString.equals("]");
                            break;
                        case "(":
                            isBalansed = currentCharAsString.equals(")");
                            break;
                        default:
                            break;
                    }
                }
            }

            if (isBalansed){
                System.out.printf("YES");
            } else {
                System.out.printf("NO");
            }
        }
    }
}
