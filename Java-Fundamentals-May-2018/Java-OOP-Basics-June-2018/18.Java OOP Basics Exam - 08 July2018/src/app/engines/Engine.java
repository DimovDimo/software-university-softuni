package app.engines;

import app.contracts.Arena;
import app.contracts.ComicCharacter;
import app.contracts.Manager;
import app.contracts.SuperPower;
import app.entities.Arena.ArenaImpl;
import app.entities.ComicCharacter.AntiHero.Titan;
import app.entities.ComicCharacter.AntiHero.Villain;
import app.entities.ComicCharacter.Hero.DCHero;
import app.entities.ComicCharacter.Hero.MarvelHero;
import app.entities.Power.Power;
import app.io.ConsoleInputReader;
import app.io.ConsoleOutputWriter;
import app.utilities.Constants;
import app.utilities.InputParser;

import java.util.List;

public class Engine {
    private ConsoleInputReader inputReader;
    private ConsoleOutputWriter outputWriter;
    private InputParser inputParser;
    private Manager warManager;
    private Constants constants;

    public Engine(ConsoleInputReader inputReader, ConsoleOutputWriter outputWriter, InputParser inputParser, Manager warManager) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.inputParser = inputParser;
        this.warManager = warManager;
        this.constants = new Constants();
    }

    public void run(){
        String inputLine;

        while (true){
            inputLine = this.inputReader.readLine();
            List<String> commandParams = this.inputParser.parseInput(inputLine);

            this.dispachCommand(commandParams);

            if (inputLine.equals(this.constants.INPUT_WAR_IS_OVER_TERMINATING_COMMAND)){
                this.outputWriter.writeLine(this.warManager.endWar());
                break;
            }
        }
    }

    private void dispachCommand(List<String> commandParams) {
        String command = commandParams.get(0);
		String result = null;

        String characterName;
        String type;
        int energy;
        double health;
        double intelligence;
        double specialForCharecter;
        ComicCharacter character = null;
        String arenaName;
        int catacity;
        String superPowerName;
        int powerPoints;

        try {
            switch (command){
                case "CHECK_CHARACTER":
                    result = checkCharacter(commandParams);
                    break;
                case "REGISTER_HERO":
                    result = registerHero(commandParams, character);
                    break;
                case "REGISTER_ANTI_HERO":
                    result = registerAntyHero(commandParams, character);
                    break;
                case "BUILD_ARENA":
                    result = buildArena(commandParams);
                    break;
                case "SEND_HERO":
                    result = sendHero(commandParams);
                    break;
                case "SEND_ANTI_HERO":
                    result = sendAntiHero(commandParams);
                    break;
                case "SUPER_POWER":
                    result = loadSuperPowerToPool(commandParams);
                    break;
                case "ASSIGN_POWER":
                    result = assignSuperPowerToComicCharacter(commandParams);
                    break;
                case "UNLEASH":
                    result = usePowers(commandParams);
                    break;
                case "COMICS_WAR":
                    result = startBattle(commandParams);
                    break;
            }
        } catch (IllegalArgumentException error){
            result = error.getMessage();
        }

		
        if (result != null){
            this.outputWriter.writeLine(result);
        }
    }

    private String startBattle(List<String> commandParams) {
        String arenaName;
        arenaName = commandParams.get(1);
        return this.warManager.startBattle(arenaName);
    }

    private String usePowers(List<String> commandParams) {
        String characterName;
        characterName = commandParams.get(1);
        return this.warManager.usePowers(characterName);
    }

    private String assignSuperPowerToComicCharacter(List<String> commandParams) {
        String characterName;
        String superPowerName;
        characterName = commandParams.get(1);
        superPowerName = commandParams.get(2);
        return this.warManager.assignSuperPowerToComicCharacter(characterName, superPowerName);
    }

    private String loadSuperPowerToPool(List<String> commandParams) {
        String superPowerName;
        int powerPoints;
        superPowerName = commandParams.get(1);
        powerPoints = Integer.parseInt(commandParams.get(2));
        SuperPower superPower = new Power(superPowerName, powerPoints);
        return this.warManager.loadSuperPowerToPool(superPower);
    }

    private String sendAntiHero(List<String> commandParams) {
        String arenaName;
        String characterName;
        arenaName = commandParams.get(1);
        characterName = commandParams.get(2);
        return this.warManager.addAntiHeroToArena(arenaName, characterName);
    }

    private String sendHero(List<String> commandParams) {
        String arenaName;
        String characterName;
        arenaName = commandParams.get(1);
        characterName = commandParams.get(2);
        return this.warManager.addHeroToArena(arenaName, characterName);
    }

    private String buildArena(List<String> commandParams) {
        String arenaName;
        int catacity;
        arenaName = commandParams.get(1);
        catacity = Integer.parseInt(commandParams.get(2));
        Arena arena = new ArenaImpl(arenaName, catacity);
        return this.warManager.addArena(arena);
    }

    private String registerAntyHero(List<String> commandParams, ComicCharacter character) {
        String characterName;
        String type;
        int energy;
        double health;
        double intelligence;
        double specialForCharecter;
        characterName = commandParams.get(1);
        type = commandParams.get(2);
        energy = Integer.parseInt(commandParams.get(3));
        health = Double.parseDouble(commandParams.get(4));
        intelligence = Double.parseDouble(commandParams.get(5));
        specialForCharecter = Double.parseDouble(commandParams.get(6));

        switch (type){
            case "Villain":
                character = new Villain(characterName, energy, health, intelligence, specialForCharecter);
                break;
            case "Titan":
                character = new Titan(characterName, energy, health, intelligence, specialForCharecter);
                break;
        }

        return this.warManager.addAntiHero(character);
    }

    private String registerHero(List<String> commandParams, ComicCharacter character) {
        String characterName;
        String type;
        int energy;
        double health;
        double intelligence;
        double specialForCharecter;
        characterName = commandParams.get(1);
        type = commandParams.get(2);
        energy = Integer.parseInt(commandParams.get(3));
        health = Double.parseDouble(commandParams.get(4));
        intelligence = Double.parseDouble(commandParams.get(5));
        specialForCharecter = Double.parseDouble(commandParams.get(6));

        switch (type){
            case "MarvelHero":
                character = new MarvelHero(characterName, energy, health, intelligence, specialForCharecter);
                break;
            case "DCHero":
                character = new DCHero(characterName, energy, health, intelligence, specialForCharecter);
                break;
        }

        return this.warManager.addHero(character);
    }

    private String checkCharacter(List<String> commandParams) {
        String characterName;
        characterName = commandParams.get(1);
        return this.warManager.checkComicCharacter(characterName);
    }
}
