package callofduty.core;

import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MissionControlTest {

    private MissionControl missionControl;
    private Mission missionQ;
    private Mission missionW;

    @Before
    public void setUp() throws Exception {
        missionControl = new MissionControlImpl();
        String missionId = "Id";
        Double missionRating = 2.0;
        Double missionBounty = 3.0;

        missionQ = missionControl.generateMission(missionId, missionRating, missionBounty);
        missionW = missionControl.generateMission(missionId, missionRating, missionBounty);
    }

    @Test
    public void generateMission() {
        String missionId = "Id";

        Assert.assertEquals("", missionId, missionQ.getId());
        Assert.assertEquals("", missionId, missionW.getId());
    }
}