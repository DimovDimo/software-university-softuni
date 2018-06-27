package p03_WildFarm.Food.Animal;

import p03_WildFarm.Food.Food.Food;

public abstract class Animal {
    private String animalName;
    private String animalType;
    private Double animalWeight;
    private Integer foodEaten;

    public Animal(String animalName, String animalType, Double animalWeight, Integer foodEaten) {
        this.setAnimalName(animalName);
        this.setAnimalType(animalType);
        this.setAnimalWeight(animalWeight);
        this.setFoodEaten(foodEaten);
    }

    public String getAnimalName() {
        return animalName;
    }

    private void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalType() {
        return animalType;
    }

    private void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public Double getAnimalWeight() {
        return animalWeight;
    }

    private void setAnimalWeight(Double animalWeight) {
        this.animalWeight = animalWeight;
    }

    public Integer getFoodEaten() {
        return foodEaten;
    }

    protected void setFoodEaten(Integer foodEaten) {
        this.foodEaten = foodEaten;
    }

    protected abstract void makeSound();

    protected abstract void eat(Food food);
}
