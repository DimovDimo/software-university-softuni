package rpg_tests;

import Interfaces.Target;
import Interfaces.Weapon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Dummy;
import rpg_lab.Hero;

public class HeroTest {
    public static final int ATTACK_POINT = 10;
    public static final int DURABILITY_POINT = 10;
    public static final int HEALTH = 10;
    public static final int XP = 10;

    private Weapon weapon;
    private Target target;

    @Before
    public void initializeWeaponAndTarget(){
        this.weapon = new Weapon() {
            public int getAttackPoints() {
                return ATTACK_POINT;
            }

            public int getDurabilityPoints() {
                return DURABILITY_POINT;
            }

            public void attack(Target target) {
                target.giveExperience();
            }
        };

        this.target = new Target() {
            public int getHealth() {
                return HEALTH;
            }

            public void takeAttack(int attackPoints) {

            }

            public int giveExperience() {
                return XP;
            }

            public boolean isDead() {
                return true;
            }
        };
    }

    @Test
    public void attackGainsXPIfTargetIsDead(){
        Hero hero = new Hero("hero", this.weapon);
        hero.attack(this.target);

        Assert.assertEquals("Wrong XP", XP, hero.getExperience());
    }
}
