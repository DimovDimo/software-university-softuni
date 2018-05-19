import java.util.ArrayDeque;
import java.util.Scanner;

public class Problem06DecimalToBinaryConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        ArrayDeque<Integer> stackBinary = new ArrayDeque<>();

        if (number == 0){
            System.out.println(0);
        } else {
            while (number != 0){
                stackBinary.push(number%2);
                number /= 2;
            }

            while (stackBinary.size() > 0){
                System.out.print(stackBinary.pop());
            }
        }
    }
}
