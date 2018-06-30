package p08_MilitaryElite.impl;

import p08_MilitaryElite.contracts.Mission;

public class MissionImpl implements Mission {

    private static final String IN_PROGRES_MISSION_STATE = "inProgress";
    private static final String FINICHED_MISSION_STATE = "Finished";

    private String codeName;
    private String state;

    public MissionImpl(String codeName, String state) {
        this.codeName = codeName;
        this.setState(state);
    }

    private void setState(String state) {
        if (!IN_PROGRES_MISSION_STATE.equals(state) && !FINICHED_MISSION_STATE.equals(state)){
            throw new IllegalArgumentException();
        }

        this.state = state;
    }

    @Override
    public void completeMission(){
        this.state = FINICHED_MISSION_STATE;
    }

    @Override
    public String toString() {
        return String.format("  Code Name: %s State: %s", this.codeName, this.state);
    }
}
