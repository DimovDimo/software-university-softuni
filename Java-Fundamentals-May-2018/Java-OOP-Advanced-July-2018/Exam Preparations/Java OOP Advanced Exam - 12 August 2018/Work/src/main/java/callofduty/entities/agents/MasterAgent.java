package callofduty.entities.agents;

import callofduty.interfaces.BountyAgent;

public class MasterAgent extends BaseAgent implements BountyAgent {

    private Double bounty;

    public MasterAgent(String id, String name, Double rating) {
        super(id, name, rating);
        this.bounty = 0.0;
    }

    @Override
    public Double getBounty() {
        this.bounty = super.getCompletedMissions().stream()
                .mapToDouble(m -> m.getBounty())
                .sum();

        return this.bounty;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Master Agent - %s", super.getName()))
                .append(System.lineSeparator())
                .append(String.format("Personal Code: %s", super.getId()))
                .append(System.lineSeparator())
                .append(String.format("Assigned Missions: %d", super.getAcceptedMissions().size()))
                .append(System.lineSeparator())
                .append(String.format("Completed Missions: %d", super.getCompletedMissions().size()))
                .append(System.lineSeparator())
                .append(String.format("Rating: %.2f", super.getRating()))
                .append(System.lineSeparator())
                .append(String.format("Bounty Earned: $%.2f", this.bounty));

        return sb.toString();
    }
}
