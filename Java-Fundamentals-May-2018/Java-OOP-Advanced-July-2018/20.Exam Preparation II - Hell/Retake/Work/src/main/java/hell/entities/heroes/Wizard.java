package hell.entities.heroes;

import hell.interfaces.Inventory;

public class Wizard extends BaseHero {

    private static final int STRENGTH = 25;
    private static final int AGILITY = 25;
    private static final int INTELLIGENCE = 100;
    private static final int HITPOINTS = 100;
    private static final int DAMAGE = 250;

    public Wizard(String name, Inventory inventory) {
        super(name, STRENGTH, AGILITY, INTELLIGENCE, HITPOINTS, DAMAGE, inventory);
    }
}
