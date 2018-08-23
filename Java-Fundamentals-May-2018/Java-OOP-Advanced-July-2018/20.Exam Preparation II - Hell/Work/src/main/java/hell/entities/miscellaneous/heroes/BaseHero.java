package hell.entities.miscellaneous.heroes;

import hell.entities.miscellaneous.ItemCollection;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseHero implements Hero {
//•	Name – a string, indicating the name of the hero.
//•	Strength – an integer, indicating the strength of the hero.
//•	Agility – an integer, indicating the agility of the hero.
//•	Intelligence – an integer, indicating the intelligence of the hero.
//•	HitPoints – an integer, indicating the hit points of the hero.
//•	Damage – an integer, indicating the damage of the hero.

    private String name;

    private int strength;

    private int agility;

    private int intelligence;

    private int hitPoints;

    private int damage;

    private Inventory inventory;

    protected BaseHero(String name, int strength, int agility, int intelligence, int hitPoints, int damage, Inventory inventory) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.inventory = inventory;
    }



    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength;
    }

    @Override
    public long getAgility() {
        return this.agility;
    }

    @Override
    public long getIntelligence() {
        return this.intelligence;
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints;
    }

    @Override
    public long getDamage() {
        return this.damage;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Item> getItems() throws IllegalAccessException {
        Collection<Item> items = null;
        Field[] inventoryFields = this.inventory.getClass().getDeclaredFields();

        for (Field inventoryField : inventoryFields) {
            if (inventoryField.isAnnotationPresent(ItemCollection.class)){
                inventoryField.setAccessible(true);
                Map<String, Item> itemMap = (Map<String, Item>) inventoryField.get(this.inventory);
                items = itemMap.values();
            }
        }
        return items;
    }

    @Override
    public void addItem(Item item) {
        this.inventory.addCommonItem(item);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.inventory.addRecipeItem(recipe);
    }
}
