package C03_Mission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Mission {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> missionsConsidered = new HashMap<>();
        Map<String, Integer> missionsFailed = new HashMap<>();

        Pattern patternMission = Pattern.compile(".*M.*I.*S.*S.*I.*O.*N.*");
        while (true){
            String line = reader.readLine();
            if ("Decrypt".equals(line)){
                break;
            }

            Matcher matcherMission = patternMission.matcher(line);
            if (matcherMission.find()){
                String mission = matcherMission.group();
                int rating = 0;
                StringBuilder nameMission = new StringBuilder();
                for (int i = 0; i < mission.length(); i++) {
                    char cuurentChar = mission.charAt(i);
                    if (Character.isDigit(cuurentChar)){
                        rating += Integer.parseInt(String.valueOf(cuurentChar));
                    } else if (Character.isLowerCase(cuurentChar)){
                        nameMission.append(String.valueOf(cuurentChar));
                    }
                }

                if (nameMission.toString().equals("") || rating == 0){
                    continue;
                }

                boolean isConsidered = Arrays.stream(mission.split("")).collect(Collectors.toList()).contains("C");
                if (isConsidered){
                    missionsConsidered.put(nameMission.toString(), rating);
                }

                boolean isFailed = Arrays.stream(mission.split("")).collect(Collectors.toList()).contains("X");
                if (isFailed) {
                    missionsFailed.put(nameMission.toString(), rating);
                }
            }
        }

        if (missionsConsidered.size() > 0){
            String bestNameMissionConsidered = "";
            int bestRatingMissionConsidered = Integer.MIN_VALUE;
            for (Map.Entry<String, Integer> mission : missionsConsidered.entrySet()) {
                if (bestRatingMissionConsidered < mission.getValue()){
                    bestNameMissionConsidered = mission.getKey();
                    bestRatingMissionConsidered = mission.getValue();
                }
            }
            System.out.printf("Completed mission %s with rating: %d%n",
                    bestNameMissionConsidered,
                    bestRatingMissionConsidered);
        }

        if (missionsFailed.size() > 0){
            String bestNameMissionFailed = "";
            int bestRatingMissionFailed = Integer.MIN_VALUE;
            for (Map.Entry<String, Integer> mission : missionsFailed.entrySet()) {
                if (bestRatingMissionFailed < mission.getValue()){
                    bestNameMissionFailed = mission.getKey();
                    bestRatingMissionFailed = mission.getValue();
                }
            }
            System.out.printf("Failed Mission %s with rating: %d%n",
                    bestNameMissionFailed,
                    bestRatingMissionFailed);
        }
    }
}
