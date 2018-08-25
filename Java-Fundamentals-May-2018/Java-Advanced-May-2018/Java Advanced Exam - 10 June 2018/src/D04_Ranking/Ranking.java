package D04_Ranking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Ranking {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, String> contests = new HashMap<>();
        Map<String, Map<String, Integer>> users = new HashMap<>();
        
        fillContests(reader, contests);
        fillUsers(reader, users, contests);
        printBestCandidate(users);
        printAllUsers(users);
    }

    private static void printAllUsers(Map<String, Map<String, Integer>> users) {
        StringBuilder resultUsers = new StringBuilder();
        resultUsers.append("Ranking:")
                .append(System.lineSeparator());
        users.entrySet().stream()
                .sorted((user1, user2) -> {
                    return  user1.getKey().compareTo(user2.getKey());
                }).forEach(user -> {
                    resultUsers.append(user.getKey())
                            .append(System.lineSeparator());
                    user.getValue().entrySet().stream()
                            .sorted((contest1, contest2) -> {
                                return contest2.getValue() - contest1.getValue();
                            }).forEach(contest -> {
                                resultUsers.append(String.format("#  %s -> %d", contest.getKey(), contest.getValue()))
                                        .append(System.lineSeparator());
                    });
        });

        System.out.println(resultUsers.toString());
    }

    private static void printBestCandidate(Map<String, Map<String, Integer>> users) {
        String bestUser = "";
        int bestTotalPoints = Integer.MIN_VALUE;
        for (Map.Entry<String, Map<String, Integer>> user : users.entrySet()) {
            int currentPoints = user.getValue().entrySet().stream()
                    .mapToInt(content -> content.getValue()).sum();
            if (bestTotalPoints < currentPoints){
                bestTotalPoints = currentPoints;
                bestUser = user.getKey();
            }
        }

        System.out.printf("Best candidate is %s with total %d points.%n", bestUser, bestTotalPoints);
    }

    private static void fillUsers(BufferedReader reader, Map<String, Map<String, Integer>> users, Map<String, String> contests) throws IOException {
        while (true){
            String line = reader.readLine();
            if ("end of submissions".equals(line)){
                break;
            }
            
            String[] tokens = line.split("=>");
            
            String contest = tokens[0];
            String password = tokens[1];
            String username = tokens[2];
            int points = Integer.parseInt(tokens[3]);

            if (contests.containsKey(contest) && contests.get(contest).equals(password)){
                if (!users.containsKey(username)){
                    users.put(username, new HashMap<>());
                }

                if (!users.get(username).containsKey(contest)){
                    users.get(username).put(contest, points);
                } else {
                    int lastBestPoints = users.get(username).get(contest);
                    if (lastBestPoints < points){
                        users.get(username).put(contest, points);
                    }
                }
            }
        }
    }

    private static void fillContests(BufferedReader reader, Map<String, String> contests) throws IOException {
        while (true){
            String line = reader.readLine();
            if ("end of contests".equals(line)){
                break;
            }
            
            String[] tokens = line.split(":");
            
            String contest = tokens[0];
            String password = tokens[1];
            contests.put(contest, password);
        }
    }
}
