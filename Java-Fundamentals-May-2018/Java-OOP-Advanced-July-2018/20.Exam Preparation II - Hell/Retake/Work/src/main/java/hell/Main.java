package hell;

import hell.entities.heroes.Assassin;
import hell.entities.heroes.Barbarian;
import hell.entities.heroes.Wizard;
import hell.entities.items.CommonItem;
import hell.entities.items.RecipeItem;
import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.*;
import hell.io.ConsoleInputReader;
import hell.io.ConsoleOutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private static Map<String, Hero> heroes = new LinkedHashMap<>();

    public static void main(String[] args) {
        InputReader reader = new ConsoleInputReader();
        OutputWriter writer = new ConsoleOutputWriter();

        while (true) {
            String[] tokens = reader.readLine().split("\\s+");

            String command = tokens[0];
            String[] arguments = Arrays.stream(tokens).skip(1).toArray(String[]::new);

            String result = interpetCommand(command, arguments);

            writer.writeLine(result);

            if ("Quit".equals(command)) {
                break;
            }
        }
    }

    private static String interpetCommand(String command, String[] arguments) {
        Hero hero;
        Item item;
        switch (command) {
            case "Hero":
                return heroCommand(arguments);
            case "Item":
                String name = arguments[0];
                String heroName = arguments[1];
                int strengthBonus = Integer.parseInt(arguments[2]);
                int agilityBonus = Integer.parseInt(arguments[3]);
                int intelligenceBonus = Integer.parseInt(arguments[4]);
                int hitpointsBonus = Integer.parseInt(arguments[5]);
                int damageBonus = Integer.parseInt(arguments[6]);

                item = new CommonItem(name,strengthBonus, agilityBonus, intelligenceBonus, hitpointsBonus, damageBonus);

                if(heroes.containsKey(heroName)){
                    heroes.get(heroName).addItem(item);
                    return String.format("Added item - %s to Hero - %s", name, heroName);
                }

            case "Recipe":
                hero = heroes.get(arguments[1]);
                Recipe recipeItem = new RecipeItem(
                        arguments[0],
                        Integer.parseInt(arguments[2]),
                        Integer.parseInt(arguments[3]),
                        Integer.parseInt(arguments[4]),
                        Integer.parseInt(arguments[5]),
                        Integer.parseInt(arguments[6]),
                        Arrays.stream(arguments).skip(7).collect(Collectors.toList())
                );
                hero.addRecipe(recipeItem);

                return String.format("Added recipe - %s to Hero - %s",
                        recipeItem.getName(),
                        hero.getName());
            case "Inspect":
                hero = heroes.get(arguments[0]);
                return hero.toString();
            case "Quit":
                return printHeroesInfo();
        }

        return null;
    }

    private static Comparator<? super Hero> getHeroesComparator() {
        return (hero1, hero2) -> {
            long firstComparatorSumHero1 = hero1.getStrength() + hero1.getAgility() + hero1.getIntelligence();
            long firstComparatorSumHero2 = hero2.getStrength() + hero2.getAgility() + hero2.getIntelligence();

            int comparatorByFirstSum = Long.compare(firstComparatorSumHero2, firstComparatorSumHero1);

            if (comparatorByFirstSum != 0) {
                return comparatorByFirstSum;
            }

            long secondComparatorSumHero1 = hero1.getHitPoints() + hero1.getDamage();
            long secondComparatorSumHero2 = hero2.getHitPoints() + hero2.getDamage();

            return Long.compare(secondComparatorSumHero2, secondComparatorSumHero1);
        };
    }

    private static String printHeroesInfo() {
        StringBuilder heroesInfo = new StringBuilder();

        final int[] index = {1};
        heroes.values()
                .stream()
                .sorted(getHeroesComparator())
                .forEach(hero -> {
                    String items = hero.getItems().size() == 0 ?
                            "None" :
                            hero.getItems()
                                    .stream()
                                    .map(Item::getName)
                                    .collect(Collectors.joining(", "));
                    heroesInfo
                            .append(String.format("%d. %s: %s",
                                    index[0]++,
                                    hero.getClass().getSimpleName(),
                                    hero.getName()))
                            .append(System.lineSeparator())
                            .append(String.format("###HitPoints: %d", hero.getHitPoints()))
                            .append(System.lineSeparator())
                            .append(String.format("###Damage: %d", hero.getDamage()))
                            .append(System.lineSeparator())
                            .append(String.format("###Strength: %d", hero.getStrength()))
                            .append(System.lineSeparator())
                            .append(String.format("###Agility: %d", hero.getAgility()))
                            .append(System.lineSeparator())
                            .append(String.format("###Intelligence: %d", hero.getIntelligence()))
                            .append(System.lineSeparator())
                            .append(String.format("###Items: %s", items))
                            .append(System.lineSeparator());
                });

        return heroesInfo.toString().trim();
    }

    private static String heroCommand(String[] arguments) {
        Hero hero;
        String name = arguments[0];
        String type = arguments[1];
        switch (type){
            case "Barbarian":
                hero = new Barbarian(name, new HeroInventory());
            case "Assassin":
                hero = new Assassin(name, new HeroInventory());
            case "Wizard":
                hero = new Wizard(name, new HeroInventory());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        heroes.put(hero.getName(), hero);
        return String.format("Created %s - %s", type, name);
    }
}