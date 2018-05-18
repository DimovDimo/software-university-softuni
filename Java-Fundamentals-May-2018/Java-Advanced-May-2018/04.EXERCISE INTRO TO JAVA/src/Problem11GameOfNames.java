import java.util.Scanner;

public class Problem11GameOfNames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countPlayers = scanner.nextInt();
        String bestName = "a";
        int bestPoints = 0;

        for (int i = 0; i < countPlayers; i++) {
            String name = scanner.next();
            int points = Integer.parseInt(scanner.next());
            for (int j = 0; j < name.length(); j++) {
                char currentChar = name.charAt(j);
                int currrentPoints = (int) currentChar;
                if (currrentPoints % 2 == 0){
                    points += currrentPoints;
                } else {
                    points -= currrentPoints;
                }
            }

            if (bestPoints < points){
                bestName = name;
                bestPoints = points;
            }
        }

        System.out.printf("The winner is %s - %d points",
                bestName, bestPoints);
    }
}
