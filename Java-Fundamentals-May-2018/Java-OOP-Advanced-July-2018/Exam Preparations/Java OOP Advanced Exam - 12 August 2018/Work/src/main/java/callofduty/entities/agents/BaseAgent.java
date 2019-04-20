package callofduty.entities.agents;

import callofduty.entities.BaseEntity;
import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;
import callofduty.interfaces.Nameable;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAgent extends BaseEntity implements Nameable, Agent {

    private String name;
    private List<Mission> acceptedMissions;
    private List<Mission> completedMissions;

    protected BaseAgent(String id, String name, Double rating) {
        super(id, rating);
        this.name = name;
        acceptedMissions = new ArrayList<>();
        completedMissions = new ArrayList<>();
    }

    protected List<Mission> getAcceptedMissions() {
        return acceptedMissions;
    }

    protected void setAcceptedMissions(List<Mission> acceptedMissions) {
        this.acceptedMissions = acceptedMissions;
    }

    protected List<Mission> getCompletedMissions() {
        return completedMissions;
    }

    protected void setCompletedMissions(List<Mission> completedMissions) {
        this.completedMissions = completedMissions;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void acceptMission(Mission mission) {
        this.acceptedMissions.add(mission);
    }

    @Override
    public void completeMissions() {
        for (Mission acceptedMission : this.acceptedMissions) {
            super.setRating(super.getRating() + acceptedMission.getRating());
//            this.bounty += acceptedMission.getBounty();
        }

        this.completedMissions.addAll(this.acceptedMissions);
        this.acceptedMissions.clear();
    }
}
