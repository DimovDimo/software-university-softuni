package p03_WildFarm.Food.Animal;

public abstract class Mammal extends Animal {
    private String livingegion;

    public Mammal(String animalName, String animalType, Double animalWeight, Integer foodEaten, String livingegion) {
        super(animalName, animalType, animalWeight, foodEaten);
        this.setLivingegion(livingegion);
    }

    public String getLivingegion() {
        return livingegion;
    }

    protected void setLivingegion(String livingegion) {
        this.livingegion = livingegion;
    }
}
