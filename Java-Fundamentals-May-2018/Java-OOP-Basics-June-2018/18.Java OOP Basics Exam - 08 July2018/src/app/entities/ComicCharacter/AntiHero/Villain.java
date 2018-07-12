package app.entities.ComicCharacter.AntiHero;

public class Villain extends AntiHero {
    public Villain(String name, int energy, double health, double intelligence, double evilness) {
        super(name, energy, health, intelligence, evilness);
    }

    @Override
    public double attack() {
        return (super.getIntelligence() * super.getSpecial()) / super.getEnergy();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
