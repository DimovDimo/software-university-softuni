package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

public class AxeTests {
    public static final int AXE_ATTACK = 10;
    public static final int AXE_DURABILITY = 10;
    public static final int DUMMY_HEALTH = 10;
    public static final int DUMMY_XP = 10;
    public static final int EXPECTED_DURABILITY = AXE_DURABILITY - 1;
    public static final int ZERO = 0;

    private Axe axe;
    private Dummy dummy;

    @Before
    public void initializedTestIbjects(){
        this.axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP);
    }

    @Test
    public void weaponLosesDurability(){
        this.axe.attack(this.dummy);
        Assert.assertEquals("Wrong Durability", EXPECTED_DURABILITY, this.axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void attackingWithABrokenWeapon(){
        Axe axeWithBokenWeapon = new Axe(AXE_ATTACK, ZERO);
        axeWithBokenWeapon.attack(dummy);
    }
}
