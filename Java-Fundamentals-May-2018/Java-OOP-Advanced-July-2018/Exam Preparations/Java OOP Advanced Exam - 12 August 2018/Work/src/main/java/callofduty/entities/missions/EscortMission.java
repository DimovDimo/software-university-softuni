package callofduty.entities.missions;

public class EscortMission extends BaseMission {

    //The EscortMission is just a normal mission.
    //Decreases its given rating by 25%.
    //Increases its given bounty by 25%.

    private static final Double RATING = 0.75;
    private static final Double BOUNTY = 1.25;

    public EscortMission(String id, Double rating, Double bounty) {
        super(id, rating * RATING, bounty * BOUNTY);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Escort Mission - %s", super.getId()))
                .append(System.lineSeparator())
                .append("Status: Open")
                .append(System.lineSeparator())
                .append(String.format("Rating: %.2f", super.getRating()))
                .append(System.lineSeparator())
                .append(String.format("Bounty: %.2f", super.getBounty()));

        return sb.toString();
    }
}
