package p03_WildFarm.Food.Animal;

import p03_WildFarm.Food.Food.Food;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private String breed;

    public Cat(String animalName, String animalType, Double animalWeight, Integer foodEaten, String livingegion, String breed) {
        super(animalName, animalType, animalWeight, foodEaten, livingegion);
        this.setBreed(breed);
    }

    public String getBreed() {
        return breed;
    }

    private void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public void eat(Food food) {
        super.setFoodEaten(super.getFoodEaten() + food.getQuantity());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getAnimalType())
                .append("[")
                .append(super.getAnimalName())
                .append(", ")
                .append(this.getBreed())
                .append(", ")
                .append(String.format("%s", new DecimalFormat("#.##").format(super.getAnimalWeight())))
                .append(", ")
                .append(super.getLivingegion())
                .append(", ")
                .append(super.getFoodEaten())
                .append("]");
        return sb.toString();
    }
}
