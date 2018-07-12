package app.entities.ComicCharacter;

import app.contracts.ComicCharacter;
import app.contracts.SuperPower;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ComicCharacterImpl implements ComicCharacter {

    private String name;
    private int energy;
    private double health;
    private double intelligence;
    private Collection<SuperPower> powers;

    public ComicCharacterImpl(String name, int energy, double health, double intelligence) {
        this.setName(name);
        this.setEnergy(energy);
        this.setHealth(health);
        this.setIntelligence(intelligence);
        this.setPowers();
    }

    private void setName(String name) {
        if (name.matches("(\\b[A-Za-z_]{2,12}\\b)") == false){
            throw new IllegalArgumentException("Comic Character name is not in the correct format!");
        }

        this.name = name;
    }

    private void setEnergy(int energy) {
        if (energy < 0 || 300 < energy){
            throw new IllegalArgumentException("Energy is not in the correct range!");
        }

        this.energy = energy;
    }

    private void setHealth(double health) {
        if (health <= 0){
            throw new IllegalArgumentException("Health should be a possitive number!");
        }

        this.health = health;
    }

    private void setIntelligence(double intelligence) {
        if (intelligence < 0 || 200 < intelligence){
            throw new IllegalArgumentException("Intelligence is not in the correct range!");
        }

        this.intelligence = intelligence;
    }

    private void setPowers() {
        this.powers = new ArrayList<>();
    }

    @Override
    public void takeDamage(double damage) {
        if (damage > 0){
            this.health -= damage;
        }
    }

    @Override
    public void boostCharacter(int energy, double health, double intelligence) {
        this.setEnergy(energy);
        this.setHealth(health);
        this.setIntelligence(intelligence);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public double getIntelligence() {
        return this.intelligence;
    }

    @Override
    public String useSuperPowers() {
        if (this.powers.isEmpty()){
            return String.format("%s has no super powers!", this.name);
        } else {
            for (SuperPower power : this.powers) {
                int newEnergy = this.energy + (int)power.getPowerPoints();
                this.setEnergy(newEnergy);

                double newHealth = this.health + (power.getPowerPoints() * 2);
                this.setHealth(newHealth);
            }

            return String.format("%s used his super powers!", this.name);
        }
    }

    @Override
    public void addSuperPower(SuperPower superPower) {
        this.powers.add(superPower);
    }

    @Override
    public boolean containsSuperPower(String superPowerName){
        for (SuperPower power : this.powers) {
            if(power.getName().equals(superPowerName)){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasNoSuperPowers(){
        if (this.powers.size() == 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder comicCharacter = new StringBuilder(String.format("#Name: %s", this.name))
                .append(System.lineSeparator())
                .append(String.format("##Health: %.2f// Energy: %d// Intelligence: %.2f",
                        this.health,
                        this.energy,
                        this.intelligence))                   .append(System.lineSeparator());

        String tapeCharacter = this.getClass().getSimpleName();

        switch (tapeCharacter){
            case "DCHero":
            case "MarvelHero":
                comicCharacter.append(String.format("###Heroism: %.2f", this.getSpecial()));
                break;
            case "Villain":
            case "Titan":
                comicCharacter.append(String.format("###Evilness: %.2f", this.getSpecial()));
                break;
        }

        comicCharacter.append(System.lineSeparator());
        switch (tapeCharacter){
            case "DCHero":
                comicCharacter.append(String.format("####DC Attack Power: %.2f", this.attack()));
                break;
            case "MarvelHero":
                comicCharacter.append(String.format("####Marvel Attack Power: %.2f", this.attack()));
                break;
            case "Villain":
                comicCharacter.append(String.format("####Villain Attack Power: %.2f", this.attack()));
                break;
            case "Titan":
                comicCharacter.append(String.format("####Titan Attack Power: %.2f", this.attack()));
                break;
        }

        return comicCharacter.toString();
    }
}
