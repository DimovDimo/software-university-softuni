package rpg_tests;

import org.junit.Assert;
import org.junit.Test;
import rpg_lab.Dummy;

public class DummyTests {

    @Test
    public void losesHealthIfAttacked(){
        Dummy dummy = new Dummy(10, 10);
        dummy.takeAttack(1);
        Assert.assertEquals(9, dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void deadDummyThrowsExceptionIfAttacked(){
        Dummy dummy = new Dummy(0, 10);
        dummy.takeAttack(1);
    }

    @Test
    public void deadDummyCanGiveXP(){
        Dummy dummy = new Dummy(0, 10);
        Assert.assertEquals(10, dummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class)
    public void aliveDummyCanNotGiveXP(){
        Dummy dummy = new Dummy(10, 10);
        dummy.giveExperience();
    }
}
