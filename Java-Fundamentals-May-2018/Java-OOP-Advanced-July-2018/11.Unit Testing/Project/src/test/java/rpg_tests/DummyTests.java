package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Dummy;

public class DummyTests {

    public static final int HEALTH = 10;
    public static final int EXPERIENCE = 10;
    public static final int ATTACK_POINTS = 10;
    public static final int EXPECTED_HEALTH = HEALTH - ATTACK_POINTS;
    public static final int EXPECTED_EXPERIENCE = EXPERIENCE;

    private Dummy dummy;

    @Before
    public void initializedTestIbjectDummy(){
        this.dummy = new Dummy(HEALTH, EXPERIENCE);
    }

    @Test
    public void losesHealthIfAttacked(){
        this.dummy.takeAttack(ATTACK_POINTS);
        Assert.assertEquals("Wrong Health", EXPECTED_HEALTH, this.dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void deadDummyThrowsExceptionIfAttacked(){
        this.dummy.takeAttack(ATTACK_POINTS);
        this.dummy.takeAttack(ATTACK_POINTS);
    }

    @Test
    public void deadDummyCanGiveXP(){
        this.dummy.takeAttack(ATTACK_POINTS);
        Assert.assertEquals("Wrong XP", EXPECTED_EXPERIENCE, this.dummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class)
    public void aliveDummyCanNotGiveXP(){
        this.dummy.giveExperience();
    }
}
