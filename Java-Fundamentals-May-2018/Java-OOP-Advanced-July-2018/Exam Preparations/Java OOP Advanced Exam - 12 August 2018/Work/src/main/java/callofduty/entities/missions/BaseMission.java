package callofduty.entities.missions;

import callofduty.entities.BaseEntity;
import callofduty.interfaces.Bountyable;
import callofduty.interfaces.Mission;

public abstract class BaseMission extends BaseEntity implements Mission {

    private Double bounty;

    protected BaseMission(String id, Double rating, Double bounty) {
        super(id, rating);
        this.bounty = bounty;
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }
}
