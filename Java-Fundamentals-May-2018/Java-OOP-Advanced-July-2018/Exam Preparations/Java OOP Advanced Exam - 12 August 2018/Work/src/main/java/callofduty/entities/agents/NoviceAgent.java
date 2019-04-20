package callofduty.entities.agents;

import callofduty.interfaces.Mission;

import java.util.ArrayList;
import java.util.List;

public class NoviceAgent extends BaseAgent {

    public NoviceAgent(String id, String name) {
        super(id, name, 0.0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Novice Agent - %s", super.getName()))
                .append(System.lineSeparator())
                .append(String.format("Personal Code: %s", super.getId()))
                .append(System.lineSeparator())
                .append(String.format("Assigned Missions: %d", super.getAcceptedMissions().size()))
                .append(System.lineSeparator())
                .append(String.format("Completed Missions: %d", super.getCompletedMissions().size()))
                .append(System.lineSeparator())
                .append(String.format("Rating: %.2f", super.getRating()));

        return sb.toString();
    }
}
