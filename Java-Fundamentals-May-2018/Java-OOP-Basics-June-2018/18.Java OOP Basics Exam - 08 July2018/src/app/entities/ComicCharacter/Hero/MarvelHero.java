package app.entities.ComicCharacter.Hero;

public class MarvelHero extends Hero {
    public MarvelHero(String name, int energy, double health, double intelligence, double heroism) {
        super(name, energy, health, intelligence, heroism);
    }

    @Override
    public double attack() {
        return ((super.getEnergy() + super.getSpecial()) * super.getIntelligence()) / 2.5;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
