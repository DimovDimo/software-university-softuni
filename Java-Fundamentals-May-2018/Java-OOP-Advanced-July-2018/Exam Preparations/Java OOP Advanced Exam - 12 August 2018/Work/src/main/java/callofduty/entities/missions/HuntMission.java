package callofduty.entities.missions;

public class HuntMission extends BaseMission {

    //The HuntMission is just a normal mission.
    //Increases its given rating by 50%.
    //Increases its given bounty by 100%.

    private static final Double RATING = 1.5;
    private static final Double BOUNTY = 2.0;

    public HuntMission(String id, Double rating, Double bounty) {
        super(id, rating * RATING, bounty * BOUNTY);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Hunt Mission - %s", super.getId()))
                .append(System.lineSeparator())
                .append("Status: Open")
                .append(System.lineSeparator())
                .append(String.format("Rating: %.2f", super.getRating()))
                .append(System.lineSeparator())
                .append(String.format("Bounty: %.2f", super.getBounty()));

        return sb.toString();
    }
}
