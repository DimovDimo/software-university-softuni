package D04_Observer.inplementations.players;

import D04_Observer.abstracts.AbstractHero;
import D04_Observer.abstracts.Logger;
import D04_Observer.interfaces.Target;

public class Warrior extends AbstractHero {

    private static final String ATTACK_MESSAGE = "%s damages %s for %s";

    public Warrior(String id, int dmg, Logger logger) {
        super(id, dmg, logger);
    }

    @Override
    protected void executeClassSpecificAttack(Target target, int dmg) {
        System.out.println(String.format(ATTACK_MESSAGE, this, target, dmg));
        target.receiveDamage(dmg);
    }
}
