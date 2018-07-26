package rpg_lab;

import Interfaces.Target;
import Interfaces.Weapon;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Hero {

    private String name;
    private int experience;
    private Weapon weapon;
    private List<Weapon> weapons;

    public Hero(String name, Weapon weapon) {
        this.name = name;
        this.experience = 0;
        this.weapon = weapon;
    }

    public String getName() {
        return this.name;
    }

    public int getExperience() {
        return this.experience;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public void attack(Target target) {
        this.weapon.attack(target);

        if (target.isDead()) {
            this.experience += target.giveExperience();
        }
    }

    public Iterable<Weapon> getInventory(){
        return Collections.unmodifiableList(this.weapons);
    }
}
