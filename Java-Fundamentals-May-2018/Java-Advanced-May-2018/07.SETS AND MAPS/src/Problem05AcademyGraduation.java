import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Problem05AcademyGraduation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double[]> graduationList = new TreeMap<>();
        int numbersOfStudents = Integer.parseInt(scanner.nextLine());
        readStudents(scanner, graduationList, numbersOfStudents);
        printAverageScores(graduationList);
    }

    private static void printAverageScores(Map<String, Double[]> graduationList) {
        for (Map.Entry<String, Double[]> entry : graduationList.entrySet()) {
            double avg = 0;
            for (Double mark : entry.getValue()) {
                avg += mark;
            }

            avg /= entry.getValue().length;
            System.out.printf("%s is graduated with %s%n", entry.getKey(), avg);
        }
    }

    private static double calculateAverage(Double[] marks) {
        double avg = 0;
        for (Double mark : marks) {
            avg += mark;
        }

        avg /= marks.length;
        return avg;
    }

    private static void readStudents(Scanner scanner, Map<String, Double[]> graduationList, int numbersOfStudents) {
        for (int i = 0; i < numbersOfStudents; i++) {
            String name = scanner.nextLine();
            String[] scoresStrings = scanner.nextLine().split(" ");
            Double[] scores = new Double[scoresStrings.length];

            for (int j = 0; j < scoresStrings.length; j++) {
                scores[j] = Double.parseDouble(scoresStrings[j]);
            }

            graduationList.put(name, scores);
        }
    }
}
