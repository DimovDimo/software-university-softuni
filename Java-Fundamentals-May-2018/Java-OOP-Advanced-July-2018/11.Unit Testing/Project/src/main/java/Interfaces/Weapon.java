package Interfaces;

import rpg_lab.Dummy;

public interface Weapon {
    int getAttackPoints();

    int getDurabilityPoints();

    void attack(Target target);
}
