import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Problem09UserLogs {
    public static void main(String[] args) throws IOException {
        Map<String, Map<String, Integer>> people = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fillPeople(people, reader);
        printResult(people);
    }

    private static void printResult(Map<String, Map<String, Integer>> people) {
        for (String user : people.keySet()) {
            System.out.println(user + ":");
            StringBuilder stringBuilder = new StringBuilder();
            makeStringBuilder(people, user, stringBuilder);
            String ips = stringBuilder.substring(0, stringBuilder.length() - 2);
            System.out.printf("%s.%n", ips);
        }
    }

    private static void makeStringBuilder(Map<String, Map<String, Integer>> people, String user, StringBuilder stringBuilder) {
        for (Map.Entry<String, Integer> userIp : people.get(user).entrySet()) {
            String currentIp = String.format("%s => %s", userIp.getKey(), userIp.getValue());
            stringBuilder.append(currentIp);
            stringBuilder.append(", ");
        }
    }

    private static void fillPeople(Map<String, Map<String, Integer>> people, BufferedReader reader) throws IOException {
        while (true) {
            String line = reader.readLine();
            if ("end".equals(line)){
                break;
            }

            String[] splited = line.split("\\s+");
            String firstIp = splited[0];
            String[] ipArray = firstIp.split("=");
            String currentIp = ipArray[1];
            String secondUser = splited[2];
            String[] users = secondUser.split("=");
            String currentUse = users[1];
            putPeople(people, currentIp, currentUse);
        }
    }

    private static void putPeople(Map<String, Map<String, Integer>> people, String currentIp, String currentUse) {
        Map<String, Integer> empty = new LinkedHashMap<>();
        people.putIfAbsent(currentUse, empty);
        people.get(currentUse)
                .putIfAbsent(currentIp, 0);
        people.get(currentUse)
                .put(currentIp, people
                .get(currentUse)
                .get(currentIp) + 1);
    }
}
