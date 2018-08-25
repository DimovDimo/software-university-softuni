package hell;

import hell.entities.miscellaneous.HeroInventory;
import hell.entities.miscellaneous.heroes.Assassin;
import hell.entities.miscellaneous.heroes.Barbarian;
import hell.entities.miscellaneous.heroes.Wizard;
import hell.entities.miscellaneous.items.CommonItem;
import hell.entities.miscellaneous.items.RecipeItem;
import hell.interfaces.*;
import hell.io.ConsoleInputReader;
import hell.io.ConsoleOutputWriter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private static Map<String, Hero> heroes = new LinkedHashMap<>();
    public static void main(String[] args) {
        InputReader inputReader = new ConsoleInputReader();
        OutputWriter outputWriter = new ConsoleOutputWriter();

        while (true){
            String line = inputReader.readLine();
            String[] tokens = line.split("\\s+");

            String command = tokens[0];
            String[] arguments = Arrays.stream(tokens).skip(1).toArray(String[]::new);

            String result = interpetCommand(command, arguments);

            outputWriter.writeLine(result);

            if ("Quit".equals(line)){
                break;
            }
        }
    }

    private static String interpetCommand(String command, String[] arguments) {
        Hero hero;
        switch (command){
            case "Hero":
                hero = createHero(arguments[0], arguments[1]);

                heroes.put(hero.getName(), hero);

                return String.format("Created %s - %s", hero.getClass().getSimpleName(), hero.getName());

            case "Item":
                hero = heroes.get(arguments[1]);
                Item commonItem = new CommonItem(
                        arguments[0],
                        Integer.parseInt(arguments[2]),
                        Integer.parseInt(arguments[3]),
                        Integer.parseInt(arguments[4]),
                        Integer.parseInt(arguments[5]),
                        Integer.parseInt(arguments[6])
                );

                hero.addItem(commonItem);

                return String.format("Added item - %s to Hero - %s", commonItem.getName(), hero.getName());

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

                return String.format("Added recipe - %s to Hero - %s", recipeItem.getName(), hero.getName());

            case "Inspect":
                return "inspect";

            case "Quit":
                return "quit";
        }

        return null;
    }

    private static Hero createHero(String name, String type) {
        switch (type){
            case "Barbarian":
                return new Barbarian(name, new HeroInventory());
            case "Assassin":
                return new Assassin(name, new HeroInventory());
            case "Wizard":
                return new Wizard(name, new HeroInventory());
        }

        return null;
    }
}