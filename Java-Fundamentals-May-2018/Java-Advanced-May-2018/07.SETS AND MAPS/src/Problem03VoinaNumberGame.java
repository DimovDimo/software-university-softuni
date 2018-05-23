import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem03VoinaNumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Integer> firstPlayer = new LinkedHashSet<>();
        Set<Integer> secondPlayer = new LinkedHashSet<>();
        fillPlayerNumbers(scanner, firstPlayer);

        fillPlayerNumbers(scanner, secondPlayer);

        for (int i = 0; i < 50; i++) {
            int firstPlayerFirstNumber = firstPlayer.iterator().next();
            firstPlayer.remove(firstPlayerFirstNumber);

            int secondtPlayerFirstNumber = secondPlayer.iterator().next();
            secondPlayer.remove(secondtPlayerFirstNumber);

            if (firstPlayerFirstNumber > secondtPlayerFirstNumber){
                firstPlayer.add(firstPlayerFirstNumber);
                firstPlayer.add(secondtPlayerFirstNumber);
            } else if (firstPlayerFirstNumber < secondtPlayerFirstNumber) {
                secondPlayer.add(firstPlayerFirstNumber);
                secondPlayer.add(secondtPlayerFirstNumber);
            }
        }

        printResult(firstPlayer, secondPlayer);
    }

    private static void printResult(Set<Integer> firstPlayer, Set<Integer> secondPlayer) {
        if (firstPlayer.size() > secondPlayer.size()){
            System.out.println("First player win!");
        } else if (firstPlayer.size() < secondPlayer.size()){
            System.out.println("Second player win!");
        } else {
            System.out.println("Draw!");
        }
    }

    private static void fillPlayerNumbers(Scanner scanner, Set<Integer> firstPlayer) {
        String[] firstLine = scanner.nextLine().split("\\s+");
        for (int i = 0; i < firstLine.length; i++) {
            int currentNumber = Integer.parseInt(firstLine[i]);
            firstPlayer.add(currentNumber);
        }
    }
}
