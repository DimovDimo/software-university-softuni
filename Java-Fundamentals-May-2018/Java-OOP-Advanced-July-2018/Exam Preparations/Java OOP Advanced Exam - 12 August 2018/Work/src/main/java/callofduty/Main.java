package callofduty;

import callofduty.core.MissionControlImpl;
import callofduty.entities.agents.MasterAgent;
import callofduty.entities.agents.NoviceAgent;
import callofduty.interfaces.*;
import callofduty.io.ConsoleInputReader;
import callofduty.io.ConsoleOutputWriter;

import java.lang.reflect.Field;
import java.util.*;

public class Main {
    private static Map<String, Agent> agents = new LinkedHashMap<>();
    private static Map<String, Mission> missions = new LinkedHashMap<>();
    private static Map<String, Mission> missionsCompleted = new LinkedHashMap<>();
//    private static Map<String, Agent> agentsNovice = new LinkedHashMap<>();
    private static Map<String, BountyAgent> agentsMaster = new LinkedHashMap<>();
    private static MissionControl missionControl = new MissionControlImpl();
    private static int agentsMasterCount = 0;

    public static void main(String[] args)  {
        InputReader reader = new ConsoleInputReader();
        OutputWriter writer = new ConsoleOutputWriter();

        while (true) {
            String[] tokens = reader.readLine().split("\\s+");

            String command = tokens[0];
            String[] arguments = Arrays.stream(tokens).skip(1).toArray(String[]::new);

            String result = interpetCommand(command, arguments);

            writer.println(result);

            if ("Over".equals(command)) {
                break;
            }
        }
    }

    private static String interpetCommand(String command, String[] arguments) {
        Agent agent;
        switch (command) {
            case "Agent":
                String id = arguments[0];
                String name = arguments[1];
                agent = new NoviceAgent(id, name);
                agents.put(id, agent);
                return String.format("Registered Agent - %s:%s", name, id);

            case "Request":
                //Assigned {missionType} Mission - {missionId} to Agent - {agentName}
                //Request 007b 1x1 25 5000
                //Assigned Escort Mission - 1x1 to Agent - Donald
                // 1x1 -> missionId
                // 007b -> AgentId
                // 25 -> missionRating?
                // 5000 -> missionBounty?
                //generateMission(String missionId, Double missionRating, Double missionBounty)
                String agentId = arguments[0];
                String missionId = arguments[1];
                Double missionRating = Double.parseDouble(arguments[2]);
                Double missionBounty = Double.parseDouble(arguments[3]);

                Mission mission = missionControl.generateMission(missionId, missionRating, missionBounty);
                missions.put(missionId, mission);
                agents.get(agentId).acceptMission(mission);

                return String.format("Assigned %s Mission - %s to Agent - %s",
                        mission.getClass().getSimpleName().replaceAll("Mission", ""),
                        mission.getId(),
                        agents.get(agentId).getName());

            case "Complete":
                String agenId = arguments[0];
                agent = agents.get(agenId);
                try {
                    Field acceptedMissionsField = agent.getClass().getSuperclass().getDeclaredField("acceptedMissions");
                    acceptedMissionsField.setAccessible(true);
                    List<Mission> missionsFields = (List<Mission>) acceptedMissionsField.get(agent);
                    for (Mission missionsField : missionsFields) {
                        missionsCompleted.put(missionsField.getId(), missionsField);
                    }
                } catch (Exception ignored) {
                    ;
                }

                agent.completeMissions();

                try {
                    Field completedMissions = agent.getClass().getSuperclass().getDeclaredField("completedMissions");
                    completedMissions.setAccessible(true);
                    List<Mission> missions = (List<Mission>) completedMissions.get(agent);

                    if(missions.size() >= 3 && !agentsMaster.containsKey(agent.getId())){
                        BountyAgent master = new MasterAgent(agent.getId(), agent.getName(), agent.getRating());
                        Field masterAcceptedMissions = master.getClass()
                                .getSuperclass()
                                .getDeclaredField("acceptedMissions");
                        masterAcceptedMissions.setAccessible(true);

                        Field agentAcceptedMissions = agent.getClass().getSuperclass().getDeclaredField("acceptedMissions");
                        agentAcceptedMissions.setAccessible(true);
                        List<Mission> missionsAgentAccepted = (List<Mission>) agentAcceptedMissions.get(agent);
                        masterAcceptedMissions.set(master, missionsAgentAccepted);

                        Field masterCompletedMissions = master.getClass()
                                .getSuperclass()
                                .getDeclaredField("completedMissions");
                        masterCompletedMissions.setAccessible(true);

                        Field agentCompletedMissions = agent.getClass().getSuperclass().getDeclaredField("completedMissions");
                        agentCompletedMissions.setAccessible(true);
                        List<Mission> missionsAgentCompleted = (List<Mission>) agentCompletedMissions.get(agent);
//                        List<Mission> missionsAgentCompletedResult = (List<Mission>) Collections.unmodifiableCollection(missionsAgentCompleted);

                        List<Mission> missionsAgentCompletedResult = new ArrayList<>();
                        for (Mission missionAgent : missionsAgentCompleted) {
//                            Field bounty = missionAgent.getClass().getSuperclass()
//                                    .getDeclaredField("bounty");
//                            bounty.setAccessible(true);
//                            bounty.set(missionAgent, 0.0);
                            Mission missionResult = missionControl.generateMission(
                                    missionAgent.getId(), missionAgent.getRating(), 0.0);
                            missionsAgentCompletedResult.add(missionResult);
                        }

                        masterCompletedMissions.set(master, missionsAgentCompletedResult);

                        agents.remove(agent.getId());
                        agents.put(master.getId(), master);
                        agentsMasterCount++;
                        agentsMaster.put(master.getId(), master);
                    }
                } catch (Exception ignored) {
                    ;
                }

                return String.format("Agent - %s:%s has completed all assigned missions.", agents.get(agenId).getName(), agenId);

            case "Status":
                String statusId = arguments[0];

                if (agents.containsKey(statusId)){
                    return agents.get(statusId).toString();
                } else if (missionsCompleted.containsKey(statusId)){
                    return missionsCompleted.get(statusId).toString().replaceAll("Open", "Completed");
                } else if (missions.containsKey(statusId)){
                    return missions.get(statusId).toString();
                }

                    break;
            case "Over":
                StringBuilder sb = new StringBuilder();

                int noviceAgents = agents.size() - agentsMasterCount;
                Double totalRatingGiven = agents.values().stream()
                        .mapToDouble(x -> x.getRating())
                        .sum();

                Double totalBountyGiven = agentsMaster.values().stream()
                        .mapToDouble(x -> x.getBounty())
                        .sum();

                sb.append(String.format("Novice Agents: %d", noviceAgents))
                        .append(System.lineSeparator())
                        .append(String.format("Master Agents: %d", agentsMasterCount))
                        .append(System.lineSeparator())
                        .append(String.format("Assigned Missions: %d", missions.size()))
                        .append(System.lineSeparator())
                        .append(String.format("Completed Missions: %d", missionsCompleted.size()))
                        .append(System.lineSeparator())
                        .append(String.format("Total Rating Given: %.2f", totalRatingGiven))
                        .append(System.lineSeparator())
                        .append(String.format("Total Bounty Given: $%.2f", totalBountyGiven));

                return sb.toString();
        }
        return null;
    }
}




