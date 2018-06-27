package p03_WildFarm.Food.Animal;

import p03_WildFarm.Food.Food.Food;

import java.text.DecimalFormat;

public class Tiger extends Felime {

    public Tiger(String animalName, String animalType, Double animalWeight, Integer foodEaten, String livingegion) {
        super(animalName, animalType, animalWeight, foodEaten, livingegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {
        if ("Meat".equals(food.getType()) == false){
            throw new IllegalArgumentException("Tigers are not eating that type of food!");
        }

        super.setFoodEaten(super.getFoodEaten() + food.getQuantity());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getAnimalType())
                .append("[")
                .append(super.getAnimalName())
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
