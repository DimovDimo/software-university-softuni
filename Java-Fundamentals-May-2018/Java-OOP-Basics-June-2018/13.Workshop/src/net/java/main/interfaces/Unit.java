package net.java.main.interfaces;

public interface Unit {

    String getName();

    int getRange();

     getPosition();

    void setXPosition(int xPosition);

    int getYPosition();

    void setYPosition(int yPosition);

    int getHealthPoints();

    void setHealthPoints(int healthPoints);

    int getEnergyPoints();

    void setEnergyPoints(int energyPoints);

    int getAttackPoints();

    int getDefencePoints();

    CombatHandler getCombatHandler();
}
