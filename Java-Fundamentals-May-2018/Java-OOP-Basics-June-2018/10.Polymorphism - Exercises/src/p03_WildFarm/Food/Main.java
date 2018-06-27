package p03_WildFarm.Food;

import p03_WildFarm.Food.Animal.*;
import p03_WildFarm.Food.Food.Food;
import p03_WildFarm.Food.Food.Meat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Animal> animals = new LinkedList<>();
        getAnimals(reader, animals);
        printAnimals(animals);
    }

    private static void printAnimals(List<Animal> animals) {
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    private static void getAnimals(BufferedReader reader, List<Animal> animals) throws IOException {
        while (true){
            String line = reader.readLine();
            if ("End".equals(line)){
                break;
            }

            String[] tokensFood = reader.readLine().split("\\s+");
            String type = tokensFood[0];
            int quantity = Integer.parseInt(tokensFood[1]);
            Food food = new Meat(type, quantity);

            String[] tokensAnimal = line.split("\\s+");
            String animalType = tokensAnimal[0];
            String animalName = tokensAnimal[1];
            Double animalWeight = Double.parseDouble(tokensAnimal[2]);
            String animalLivingRegion = tokensAnimal[3];
            Integer foodEaten = 0;
            try {
                switch (animalType){
                    case "Cat":
                        String breed = tokensAnimal[4];
                        Animal cat = new Cat(animalName, animalType, animalWeight, foodEaten, animalLivingRegion, breed);
                        animals.add(cat);
                        ((Cat) cat).makeSound();
                        ((Cat) cat).eat(food);
                        break;
                    case "Mouse":
                        Animal mouse = new Mouse(animalName, animalType, animalWeight, foodEaten, animalLivingRegion);
                        animals.add(mouse);
                        ((Mouse) mouse).makeSound();
                        ((Mouse) mouse).eat(food);
                        break;
                    case "Tiger":
                        Animal tiger = new Tiger(animalName, animalType, animalWeight, foodEaten, animalLivingRegion);
                        animals.add(tiger);
                        ((Tiger) tiger).makeSound();
                        ((Tiger) tiger).eat(food);
                        break;
                    case "Zebra":
                        Animal zebra = new Zebra(animalName, animalType, animalWeight, foodEaten, animalLivingRegion);
                        animals.add(zebra);
                        ((Zebra) zebra).makeSound();
                        ((Zebra) zebra).eat(food);
                        break;
                    default:
                        break;
                }
            } catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void addNewAnimal(List<Animal> animals, Food food, String[] tokensAnimal, String animalType, String animalName, Double animalWeight, String animalLivingRegion, Integer foodEaten) {
        try {
            switch (animalType){
                case "Cat":
                    String breed = tokensAnimal[3];
                    Animal cat = new Cat(animalName, animalType, animalWeight, foodEaten, animalLivingRegion, breed);
                    ((Cat) cat).makeSound();
                    ((Cat) cat).eat(food);
                    animals.add(cat);
                    break;
                case "Mouse":
                    Animal mouse = new Mouse(animalName, animalType, animalWeight, foodEaten, animalLivingRegion);
                    ((Mouse) mouse).makeSound();
                    ((Mouse) mouse).eat(food);
                    animals.add(mouse);
                    break;
                case "Tiger":
                    Animal tiger = new Tiger(animalName, animalType, animalWeight, foodEaten, animalLivingRegion);
                    ((Tiger) tiger).makeSound();
                    ((Tiger) tiger).eat(food);
                    animals.add(tiger);
                    break;
                case "Zebra":
                    Animal zebra = new Zebra(animalName, animalType, animalWeight, foodEaten, animalLivingRegion);
                    ((Zebra) zebra).makeSound();
                    ((Zebra) zebra).eat(food);
                    animals.add(zebra);
                default:
                    break;
            }
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }
}
