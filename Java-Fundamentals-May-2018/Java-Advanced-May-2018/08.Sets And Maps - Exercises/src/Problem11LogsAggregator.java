import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Problem11LogsAggregator {
    public static void main(String[] args) throws IOException {
        Map<String, Map<String, Integer>> logs = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int countLogs = Integer.parseInt(reader.readLine());
        fillLogs(logs, reader, countLogs);
        printResult(logs);
    }

    private static void printResult(Map<String, Map<String, Integer>> logs) {
        for (Map.Entry<String, Map<String, Integer>> logsEntry : logs.entrySet()) {
            System.out.printf("%s: %d [%s]%n", logsEntry.getKey(),
                    logsEntry.getValue()
                            .values()
                            .stream()
                            .mapToInt(Integer::valueOf)
                            .sum(),
                    String.join(", ", logsEntry.getValue()
                            .keySet()));
        }
    }

    private static void fillLogs(Map<String, Map<String, Integer>> logs, BufferedReader reader, int countLogs) throws IOException {
        for (int i = 0; i < countLogs; i++) {
            String[] inputs = reader.readLine().split("\\s+");
            String ip = inputs[0];
            String currentName = inputs[1];
            int countCurrentLogs = Integer.parseInt(inputs[2]);
            putLogs(logs, ip, currentName, countCurrentLogs);
        }
    }

    private static void putLogs(Map<String, Map<String, Integer>> logs, String ip, String currentName, int countCurrentLogs) {
        logs.putIfAbsent(currentName, new TreeMap<>());
        logs.get(currentName).putIfAbsent(ip, 0);
        logs.get(currentName).put(ip, logs.get(currentName).get(ip) + countCurrentLogs);
    }
}
