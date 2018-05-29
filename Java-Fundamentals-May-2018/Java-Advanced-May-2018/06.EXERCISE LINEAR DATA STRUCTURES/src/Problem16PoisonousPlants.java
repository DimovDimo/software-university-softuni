import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Problem16PoisonousPlants {
    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = buffer.readLine();
        int numberOfPlants = Integer.valueOf(firstLine);
        String secondLine = buffer.readLine();
        String[] plants = secondLine.split(" ");
        ArrayDeque<Integer> poisonous = new ArrayDeque<>();
        poisonousPlants(numberOfPlants, plants, poisonous);
    }

    private static void poisonousPlants(int numberOfPlants, String[] plants, ArrayDeque<Integer> poisonous) {
        int[] daysArray = getDayArray(numberOfPlants, plants, poisonous);
        int maxDays = getMaxDays(daysArray);
        printMaxLiveDays(maxDays);
    }

    private static void printMaxLiveDays(int maxDays) {
        System.out.println(maxDays);
    }

    private static int getMaxDays(int[] daysArray) {
        int maxDays = Integer.MIN_VALUE;
        for (int currentDay : daysArray) {
            if (currentDay > maxDays) {
                maxDays = currentDay;
            }
        }
        return maxDays;
    }

    private static int[] getDayArray(int numberOfPlants, String[] plants, ArrayDeque<Integer> poisonous) {
        int[] daysArray = new int[numberOfPlants];
        for (int i = 0; i < numberOfPlants; i++) {
            startFromZero(poisonous, i);
            int daysInLive = 0;
            while (poisonous.size() > 0 && Integer.valueOf(plants[poisonous.peek()]) >= Integer.valueOf(plants[i])) {
                daysInLive = Math.max(daysInLive, daysArray[poisonous.pop()]);
            }

            if (poisonous.size() > 0) {
                daysArray[i] = daysInLive + 1;
            }

            poisonous.push(i);
        }
        return daysArray;
    }

    private static void checkPoisonousSize(ArrayDeque<Integer> poisonous, int[] daysArray, int i, int daysInLive) {
        if (poisonous.size() > 0) {
            daysArray[i] = daysInLive + 1;
        }
    }

    private static void startFromZero(ArrayDeque<Integer> poisonous, int i) {
        if (i == 0){
            poisonous.push(0);
        }
    }
}
