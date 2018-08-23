package hell.entities.miscellaneous.items;

import hell.interfaces.Item;

public abstract class BaseItem implements Item {

//•	Name – a string, indicating the name of the item.
//•	StrengthBonus – an integer, indicating the strength bonus of the item.
//•	AgilityBonus – an integer, indicating the agility bonus of the item.
//•	IntelligenceBonus – an integer, indicating the intelligence bonus of the item.
//•	HitPointsBonus – an integer, indicating the hit points bonus of the item.
//•	DamageBonus – an integer, indicating the damage bonus of the item.

    private String name;

    private int strengthBonus;

    private int agilityBonus;

    private int intelligenceBonus;

    private int hitPointsBonus;

    private int damageBonus;

    protected BaseItem(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPointsBonus, int damageBonus) {
        this.name = name;
        this.strengthBonus = strengthBonus;
        this.agilityBonus = agilityBonus;
        this.intelligenceBonus = intelligenceBonus;
        this.hitPointsBonus = hitPointsBonus;
        this.damageBonus = damageBonus;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStrengthBonus() {
        return strengthBonus;
    }

    @Override
    public int getAgilityBonus() {
        return agilityBonus;
    }

    @Override
    public int getIntelligenceBonus() {
        return intelligenceBonus;
    }

    @Override
    public int getHitPointsBonus() {
        return hitPointsBonus;
    }

    @Override
    public int getDamageBonus() {
        return damageBonus;
    }
}
