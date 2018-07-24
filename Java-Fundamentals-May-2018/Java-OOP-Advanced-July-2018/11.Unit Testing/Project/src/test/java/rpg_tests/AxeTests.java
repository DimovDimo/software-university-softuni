package rpg_tests;

import org.junit.Assert;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

public class AxeTests {

    @Test
    public void weaponLosesDurability(){
        Axe axe = new Axe(10, 10);
        Dummy dummy = new Dummy(10, 10);
        axe.attack(dummy);
        Assert.assertEquals(9, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void attackingWithABrokenWeapon(){
        Axe axe = new Axe(10, 0);
        Dummy dummy = new Dummy(10, 10);
        axe.attack(dummy);
    }
}
