import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;

public class Problem11Robotics {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long currentRobotTime = 0;
        String input;
        Deque<String> products = new ArrayDeque<>();
        String[] elements = reader.readLine().split(";");
        int lenghtArray = elements.length;
        String[] bots =  new String[lenghtArray];
        int[] process = new int[lenghtArray];
        int[] timeOfProcess = new int[lenghtArray];
        for (int i = 0; i < elements.length; i++) {
            String[] robot = elements[i].split("-");
            String name = robot[0];
            int time = Integer.parseInt(robot[1]);
            bots[i] = name;
            timeOfProcess[i] = time;
        }

        String[] splitTime = reader.readLine().split(":");
        currentRobotTime = getCurrentRobotTime(splitTime);
        fillProducts(reader, products);
        processingProducts(currentRobotTime, products, bots, process, timeOfProcess);
    }

    private static void processingProducts(long currentRobotTime, Deque<String> products, String[] bots, int[] process, int[] timeOfProcess) {
        while (! products.isEmpty()) {
            String currentProduct = products.removeFirst();
            currentRobotTime = getCurrentRobotTime(currentRobotTime, process);
            boolean working = isWorking(currentRobotTime, bots, process, timeOfProcess, currentProduct);
            checkWorking(products, currentProduct, working);
        }
    }

    private static long getCurrentRobotTime(long currentRobotTime, int[] process) {
        currentRobotTime += 1;
        for (int i = 0; i < process.length; i++) {
            if (process[i] == 0){
                process[i] = 0;
            } else {
                process[i] = process[i] - 1;
            }
        }
        return currentRobotTime;
    }

    private static boolean isWorking(long currentRobotTime, String[] bots, int[] process, int[] timeOfProcess, String currentProduct) {
        boolean working = true;

        for (int i = 0; i < process.length; i++) {
            if (process[i] == 0) {
                working = false;
                printResult(String.format("%s - %s [%s]", bots[i], currentProduct, timeForRobot(currentRobotTime)));
                process[i] = timeOfProcess[i];
                break;
            }
        }
        return working;
    }

    private static void printResult(String format) {
        System.out.println(format);
    }

    private static void checkWorking(Deque<String> products, String currentProduct, boolean working) {
        if (working) {
            products.addLast(currentProduct);
        }
    }

    private static long getCurrentRobotTime(String[] splitTime) {
        long currentRobotTime;
        int hours = Integer.parseInt(splitTime[0]);
        int minutes = Integer.parseInt(splitTime[1]);
        int seconds = Integer.parseInt(splitTime[2]);
        seconds += (minutes * 60);
        seconds += (hours * (60 * 60));
        currentRobotTime = seconds;
        return currentRobotTime;
    }

    private static void fillProducts(BufferedReader reader, Deque<String> products) throws IOException {
        String input;
        while ("End".equals(input = reader.readLine()) == false) {
            products.addLast(input);
        }
    }

    private static String timeForRobot(long totalSeconds) {
        DecimalFormat decimalFormat = new DecimalFormat("00");
        long hours = totalSeconds % (24 * 60 * 60) / (60 * 60);
        long minutes = totalSeconds % (60 * 60) / 60;
        long seconds = totalSeconds % 60;
        return String.format("%s:%s:%s", decimalFormat.format(hours), decimalFormat.format(minutes), decimalFormat.format(seconds));
    }

    //public static void main(String[] args) {
    //    Scanner scanner = new Scanner(System.in);
    //    Map<String, Integer> robots = new LinkedHashMap<>();
    //    readRobots(scanner, robots);
    //    long startTimeSeconds = readStartTimeSeconds(scanner);
    //    long currentTime = startTimeSeconds;
    //    ArrayDeque<String> products = new ArrayDeque<>();
    //    readProducts(scanner, products);
    //    processingProducts(robots, startTimeSeconds, currentTime, products);
    //}
//
    //private static void processingProducts(Map<String,Integer> robots, long startTimeSeconds, long currentTime, ArrayDeque<String> products) {
    //    while (true){
    //        if (products.isEmpty()){
    //            break;
    //        } else {
    //            for (Map.Entry<String, Integer> robotEntry : robots.entrySet()) {
    //                if (products.isEmpty()){
    //                    break;
    //                } else if ((currentTime - startTimeSeconds) % robotEntry.getValue() == 0){
//
    //                }
    //            }
    //        }
    //    }
    //}
//
    //private static void readProducts(Scanner scanner, ArrayDeque<String> products) {
    //    while (true){
    //        String product = scanner.nextLine();
    //        if ("End".equals(product)){
    //            break;
    //        } else {
    //            products.add(product);
    //        }
    //    }
    //}
//
    //private static long readStartTimeSeconds(Scanner scanner) {
    //    String[] timeComponents = scanner.nextLine().split(":");
    //    long hours = Integer.parseInt(timeComponents[0]);
    //    long minutes = Integer.parseInt(timeComponents[1]);
    //    long seconds = Integer.parseInt(timeComponents[2]);
    //    return (hours * 3600) + (minutes * 60) + seconds;
    //}
//
    //private static void readRobots(Scanner scanner, Map<String,Integer> robots) {
    //    String[] robotsArray = scanner.nextLine().split(";");
    //    for (String robot : robotsArray) {
    //        String[] components = robot.split("-");
    //        String name = components[0];
    //        int processTime = Integer.parseInt(components[1]);
    //        robots.put(name, processTime);
    //    }
    //}
}
