import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem02SetsOfElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Integer> firstSet = new LinkedHashSet<>();
        Set<Integer> secondSet = new LinkedHashSet<>();
        String[] lenghtOfSets = scanner.nextLine().split("\\s+");
        int firstSetLenght = Integer.parseInt(lenghtOfSets[0]);
        int secondSetLenght = Integer.parseInt(lenghtOfSets[1]);
        fillSet(scanner, firstSet, firstSetLenght);
        fillSet(scanner, secondSet, secondSetLenght);
        Set<Integer> result = new LinkedHashSet<>();
        findAllNonRepetingElement(firstSet, secondSet, result);
        printResult(result);
    }

    private static void printResult(Set<Integer> result) {
        for (Integer currentNum : result) {
            System.out.print(currentNum + " ");
        }
    }

    private static void findAllNonRepetingElement(Set<Integer> firstSet, Set<Integer> secondSet, Set<Integer> result) {
        for (Integer currentNum : firstSet) {
            if (secondSet.contains(currentNum)){
                result.add(currentNum);
            }
        }
    }

    private static void fillSet(Scanner scanner, Set<Integer> firstSet, int firstSetLenght) {
        for (int i = 0; i < firstSetLenght; i++) {
            int currentNum = Integer.parseInt(scanner.nextLine());
            firstSet.add(currentNum);
        }
    }
}
