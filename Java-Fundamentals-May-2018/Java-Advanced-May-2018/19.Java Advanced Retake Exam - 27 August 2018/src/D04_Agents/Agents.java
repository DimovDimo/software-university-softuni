package D04_Agents;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Agents {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Double> missions = new HashMap<>();
        Map<String, Map<String, Double>> agents = new LinkedHashMap<>();

        while (true){
            String line = reader.readLine();
            if ("registration".equals(line)){
                break;
            }

            if (line.startsWith("#")){
                String[] missionTokens = line.split(":");
                if (Double.parseDouble(missionTokens[1]) > 0){
                    missions.put(missionTokens[0], Double.parseDouble(missionTokens[1]));
                }

            } else if (line.length() >= 3){
                if (line.substring(line.length() - 3).startsWith("0")){
                    agents.put(line, new HashMap<>());
//                    System.out.printf(line.substring(line.length() - 3));
                }
            }
        }

//        try {
            while (true) {
//            for (int i = 0; i < 100; i++) {
                String line = reader.readLine();
                if ("operate".equals(line)) {
                    break;
                }

                String[] tokens = line.split("->");
                String command = "assign";

                command = tokens[0];

                String agentName;
                String missionName;
                String agentNameForMission;
                switch (command) {
                    case "assign":

                        agentName = tokens[1];
                        missionName = tokens[2];
                        if (agents.containsKey(agentName) &&
                                missions.containsKey(missionName)){
                            if (agents.get(agentName).containsKey(missionName) == false){
                                agents.get(agentName).put(missionName, missions.get(missionName));
                            }
                        }


                        break;
                    case "abort":

                        missionName = tokens[1];
                        if (missions.containsKey(missionName)) {
                            for (Map.Entry<String, Map<String, Double>> agent : agents.entrySet()) {
//                                agent.getValue().remove(missionName);
                                if (agent.getValue().containsKey(missionName)) {
                                    agent.getValue().remove(missionName);
                                }
                            }
                        }


                        break;
                    case "change":

                        agentName = tokens[1];
                        agentNameForMission = tokens[2];

                        if (agentName.equals(agentNameForMission) == false) {
                            if (agents.containsKey(agentName) && agents.containsKey(agentNameForMission)) {
                                Map<String, Double> firstAgentMissions = new LinkedHashMap<>();
                                for (Map.Entry<String, Double> mission : agents.get(agentName).entrySet()) {
                                    firstAgentMissions.put(mission.getKey(), mission.getValue());
                                }

                                Map<String, Double> secondAgentMissions = new LinkedHashMap<>();
                                for (Map.Entry<String, Double> mission : agents.get(agentNameForMission).entrySet()) {
                                    secondAgentMissions.put(mission.getKey(), mission.getValue());
                                }

//                    agents.get(agentName).clear();
//                    agents.get(agentNameForMission).clear();

//                    agents.get(agentName).putAll(secondAgentMissions);
//                    agents.get(agentNameForMission).putAll(firstAgentMissions);


                                firstAgentMissions.entrySet().stream()
                                        .forEach(mission -> agents.get(agentName).remove(mission.getKey()));
                                secondAgentMissions.entrySet().stream()
                                        .forEach(mission -> agents.get(agentNameForMission).remove(mission.getKey()));

                                secondAgentMissions.entrySet().stream()
                                        .forEach(mission -> {
                                            agents.get(agentName)
                                                    .put(mission.getKey(), mission.getValue());
                                        });
                                firstAgentMissions.entrySet().stream()
                                        .forEach(mission -> agents.get(agentNameForMission)
                                                .put(mission.getKey(), mission.getValue()));


                            }
                        }
                        break;
                }

            }

//        } catch (Exception e){
//            System.out.printf("");
//        }

        StringBuilder output = new StringBuilder();
        agents.entrySet().stream()
                .filter(a -> a.getValue().size() > 0)
                .sorted((agent1, agent2) -> {
                    double totalRatinAgent1 = agent1.getValue()
                            .values()
                            .stream()
                            .mapToDouble(d -> d)
                            .sum();
                    double totalRatinAgent2 = agent2.getValue()
                            .values()
                            .stream()
                            .mapToDouble(d -> d)
                            .sum();
                    double comparatorByTotalRatin = totalRatinAgent2 - totalRatinAgent1;
                    if (comparatorByTotalRatin == 0){
                        return -1;
                    } else if (comparatorByTotalRatin > 0){
                        return 1;
                    } else {
                        return -1;
                    }
                }).forEach(agent -> {
            double totalRatin = agent.getValue()
                    .values()
                    .stream()
                    .mapToDouble(d -> d)
                    .sum();
            output.append(String.format("Agent: %s - Total Rating: %.2f", agent.getKey(), totalRatin))
                    .append(System.lineSeparator());

            agent.getValue().entrySet().stream()
                    .sorted((mission1, mission2) -> {
                        double ratingMission1 = mission1.getValue();
                        double ratingMission2 = mission2.getValue();
                        double comparatorByMissionRating = ratingMission2 - ratingMission1;
                        if (comparatorByMissionRating == 0){
                            return 0;
                        } else if (comparatorByMissionRating > 0){
                            return 1;
                        } else {
                            return -1;
                        }
                    }).forEach(mission -> {
                output.append(String.format(" - %s -> %.2f", mission.getKey(), mission.getValue()))
                        .append(System.lineSeparator());
            });
        });

        System.out.println(output.toString());
    }
}
