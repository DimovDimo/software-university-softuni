package p08_PokemonTrainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Trainer> trainers = new HashMap<>();
        readTrainers(reader, trainers);
        readElements(reader, trainers);
        printResult(trainers);
    }

    private static void printResult(Map<String, Trainer> trainers) {
        trainers.values().stream()
                .sorted(Comparator.comparing(Trainer::getBadges, Comparator.reverseOrder()))
                .forEach(t -> {
                    System.out.printf("%s %d %d%n", t.getName(), t.getBadges(), t.getPokemons().size());
                });
    }

    private static void readElements(BufferedReader reader, Map<String, Trainer> trainers) throws IOException {
        while (true){
            String element = reader.readLine();
            if ("End".equals(element)){
                break;
            }

            if(element.equals("Fire") || element.equals("Water") || element.equals("Electricity")) {
                for (Map.Entry<String, Trainer> trainerEntry : trainers.entrySet()) {
                    boolean isBagetPlus = false;
                    isBagetPlus = setBagetPlus(element, trainerEntry, isBagetPlus);
                    downHelt(trainerEntry, isBagetPlus);
                }
            }
        }
    }

    private static void downHelt(Map.Entry<String, Trainer> trainerEntry, boolean isBagetPlus) {
        if (isBagetPlus == false){
            for (Pokemon pokemon : trainerEntry.getValue().getPokemons()) {
                long healt = pokemon.getHealt();
                pokemon.setHealt(healt - 10);
            }

            trainerEntry.getValue().getPokemons().removeIf(pokemon -> pokemon.getHealt() <= 0);
        }
    }

    private static boolean setBagetPlus(String element, Map.Entry<String, Trainer> trainerEntry, boolean isBagetPlus) {
        for (Pokemon pokemon : trainerEntry.getValue().getPokemons()) {
            if (pokemon.getElement().equals(element)){
                int bages = trainerEntry.getValue().getBadges();
                trainerEntry.getValue().setBadges(bages + 1);
                isBagetPlus = true;
                break;
            }
        }
        return isBagetPlus;
    }

    private static void readTrainers(BufferedReader reader, Map<String, Trainer> trainers) throws IOException {
        while (true){
            String line = reader.readLine();
            if ("Tournament".equals(line)){
                break;
            }

            String[] tokens = line.split("\\s+");
            String trainerName = tokens[0];
            String pokemonName = tokens[1];
            String pokemonElement =  tokens[2];
            long pokemonHealth = Integer.parseInt(tokens[3]);
            if (trainers.containsKey(trainerName)){
                setNewPokemon(trainers, trainerName, pokemonName, pokemonElement, pokemonHealth);
            } else {
                setNewTrainer(trainers, trainerName, pokemonName, pokemonElement, pokemonHealth);
            }
        }
    }

    private static void setNewPokemon(Map<String, Trainer> trainers, String trainerName, String pokemonName, String pokemonElement, long pokemonHealth) {
        Pokemon pokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);
        trainers.get(trainerName).getPokemons().add(pokemon);
    }

    private static void setNewTrainer(Map<String, Trainer> trainers, String trainerName, String pokemonName, String pokemonElement, long pokemonHealth) {
        Pokemon pokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);
        List<Pokemon> pokemons = new LinkedList<>();
        pokemons.add(pokemon);
        Trainer trainer = new Trainer(trainerName,pokemons);
        trainers.put(trainerName, trainer);
    }
}
