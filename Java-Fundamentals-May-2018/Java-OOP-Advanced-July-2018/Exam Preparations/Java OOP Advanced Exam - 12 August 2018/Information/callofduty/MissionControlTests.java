package callofduty;

import callofduty.core.MissionControlImpl;
import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MissionControlTests {
    private static final Double SURVEILLANCE_MISSION_RATING_MULTIPLIER = 1.5D;

    private static final Double SURVEILLANCE_MISSION_BOUNTY_MULTIPLIER = 2D;

    private Mission firstMission;

    private Mission secondMission;

    private Mission thirdMission;

    private Mission incorrectMinimalBountyAndRatingMission;

    private Mission incorrectMaximumBountyAndRatingMission;

    @Before
    public void setUp() {
        MissionControl missionControl = new MissionControlImpl();

        String missionId = "1q2w3e4r5t";
        String secondMissionId = "4r3e2w1q";
        String thirdMissionId = "aslpls";

        String incorrectMinimalBountyAndMissionMissionId = "incorrect1";
        String incorrectMaximumBountyAndMissionMissionId = "incorrect2";

        Double missionRating = 0D;
        Double missionBounty = 0D;

        Double incorrectMinimalMissionRating = -10D;
        Double incorrectMaximumMissionRating = 1000D;

        Double incorrectMinimalMissionBounty = -10D;
        Double incorrectMaximumMissionBounty = 1000000D;

        this.firstMission = missionControl.generateMission(missionId, missionRating, missionBounty);
        this.secondMission = missionControl.generateMission(secondMissionId, missionRating, missionBounty);
        this.thirdMission = missionControl.generateMission(thirdMissionId, missionRating, missionBounty);
        this.incorrectMinimalBountyAndRatingMission = missionControl.generateMission(incorrectMinimalBountyAndMissionMissionId,
                incorrectMinimalMissionRating,
                incorrectMinimalMissionBounty);
        this.incorrectMaximumBountyAndRatingMission = missionControl.generateMission(incorrectMaximumBountyAndMissionMissionId,
                incorrectMaximumMissionRating,
                incorrectMaximumMissionBounty);
    }

    @Test
    public void testGenerateMission_withLongId_shouldReturnSubstringedId() {
        String expectedId = "1q2w3e4r";
        String actualId = this.firstMission.getId();

        Assert.assertEquals("MissionControl class, generateMission() method does not work properly.", expectedId, actualId);
    }

    @Test
    public void testGenerateMission_withIncorrectMinimalRating_shouldReturnMinimalRating() {
        Double expectedRating = 0D;
        Double actualRating = this.incorrectMinimalBountyAndRatingMission.getRating();

        Assert.assertEquals("MissionControl class, generateMission() method does not work properly.", expectedRating, actualRating);
    }

    @Test
    public void testGenerateMission_withIncorrectMaximumRating_shouldReturnMaximumRating() {
        //The Incorrect Maximum Bounty and Rating Mission is of Surveillance Type
        //It modifies its given values
        Double expectedRating = 100 * SURVEILLANCE_MISSION_RATING_MULTIPLIER;
        Double actualRating = this.incorrectMaximumBountyAndRatingMission.getRating();

        Assert.assertEquals("MissionControl class, generateMission() method does not work properly.", expectedRating, actualRating);
    }

    @Test
    public void testGenerateMission_withIncorrectMinimumBounty_shouldReturnMinimumBounty() {
        Double expectedBounty = 0D;
        Double actualBounty = this.incorrectMinimalBountyAndRatingMission.getBounty();

        Assert.assertEquals("MissionControl class, generateMission() method does not work properly.", expectedBounty, actualBounty);
    }

    @Test
    public void testGenerateMission_withIncorrectMaximumBounty_shouldReturnMaximumBounty() {
        //The Incorrect Maximum Bounty and Rating Mission is of Surveillance Type
        //It modifies its given values
        Double expectedBounty = 100000D * SURVEILLANCE_MISSION_BOUNTY_MULTIPLIER;
        Double actualBounty = this.incorrectMaximumBountyAndRatingMission.getBounty();

        Assert.assertEquals("MissionControl class, generateMission() method does not work properly.", expectedBounty, actualBounty);
    }

    @Test
    public void testGenerateMission_generateSecondMission_shouldReturnHuntMissionType() {
        String expectedType = "HuntMission";
        String actualType = this.secondMission.getClass().getSimpleName();

        Assert.assertEquals("MissionControl class, generateMission() method does not work properly.", expectedType, actualType);
    }

    @Test
    public void testGenerateMission_generateThirdMission_shouldReturnSurveillanceMissionType() {
        String expectedType = "SurveillanceMission";
        String actualType = this.thirdMission.getClass().getSimpleName();

        Assert.assertEquals("MissionControl class, generateMission() method does not work properly.", expectedType, actualType);
    }
}
