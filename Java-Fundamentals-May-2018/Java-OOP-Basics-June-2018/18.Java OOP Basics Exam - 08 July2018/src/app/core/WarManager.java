package app.core;

import app.contracts.Arena;
import app.contracts.ComicCharacter;
import app.contracts.Manager;
import app.contracts.SuperPower;

import java.util.HashMap;
import java.util.Map;

public class WarManager implements Manager {
    private Map<String, ComicCharacter> comicCharacters;
    private Map<String, Arena> arenes;
    private Map<String, SuperPower> superPowerPool;
    private int battlesWonHeroes;
    private int battlesWonAntiHeroes;

    public WarManager() {
        this.comicCharacters = new HashMap<>();
        this.arenes = new HashMap<>();
        this.superPowerPool = new HashMap<>();
        this.battlesWonHeroes = 0;
        this.battlesWonAntiHeroes = 0;
    }

    @Override
    public String checkComicCharacter(String characterName) {
        if (this.comicCharacters.containsKey(characterName) == false){
            return String.format("Sorry, fans! %s doesn't exist in our comics!", characterName);
        }

        if (this.comicCharacters.get(characterName).getHealth() <= 0){
            return String.format("%s has fallen in battle!", characterName);
        }

        return this.comicCharacters.get(characterName).toString();
    }

    @Override
    public String addHero(ComicCharacter hero) {
        if (this.comicCharacters.containsKey(hero.getName())){
            this.comicCharacters.get(hero.getName())
            .boostCharacter(hero.getEnergy(), hero.getHealth(), hero.getIntelligence());
            return String.format("%s evolved!", hero.getName());

        } else {
            this.comicCharacters.put(hero.getName(), hero);
            return String.format("%s is ready for battle!", hero.getName());
        }
    }

    @Override
    public String addAntiHero(ComicCharacter antiHero) {
        if (this.comicCharacters.containsKey(antiHero.getName())){
            this.comicCharacters.get(antiHero.getName())
                    .boostCharacter(antiHero.getEnergy(), antiHero.getHealth(), antiHero.getIntelligence());
            return String.format("%s is getting stronger!", antiHero.getName());

        } else {
            this.comicCharacters.put(antiHero.getName(), antiHero);
            return String.format("%s is ready for destruction!", antiHero.getName());
        }
    }

    @Override
    public String addArena(Arena arena) {
        if (this.arenes.containsKey(arena.getArenaName())){
            return "A battle is about to start there!";

        } else {
            this.arenes.put(arena.getArenaName(), arena);
            return String.format("%s is becoming a fighting ground!", arena.getArenaName());
        }
    }

    @Override
    public String addHeroToArena(String arena, String hero) {
        if (this.arenes.containsKey(arena) && this.comicCharacters.containsKey(hero)){
            if (this.arenes.get(arena).containsHero(this.comicCharacters.get(hero))){
                return String.format("%s is fighting!", hero);
            } else if (this.comicCharacters.get(hero).getHealth() <= 0){
                return String.format("%s is dead!", hero);
            } else if (this.arenes.get(arena).isArenaFull()){
                return "Arena is full!";
            } else {
                this.arenes.get(arena).addHero(this.comicCharacters.get(hero));
                return String.format("%s is fighting for your freedom in %s!", hero, arena);
            }
        }

        return null;
    }

    @Override
    public String addAntiHeroToArena(String arena, String antiHero) {
        if (this.arenes.containsKey(arena) && this.comicCharacters.containsKey(antiHero)){
            if (this.arenes.get(arena).containsAntiHero(this.comicCharacters.get(antiHero))){
                return String.format("%s is fighting!", antiHero);
            } else if (this.comicCharacters.get(antiHero).getHealth() <= 0){
                return String.format("%s is dead!", antiHero);
            } else if (this.arenes.get(arena).isArenaFull()){
                return "Arena is full!";
            } else {
                this.arenes.get(arena).addHero(this.comicCharacters.get(antiHero));
                return String.format("%s and his colleagues are trying to take over %s!", antiHero, arena);
            }
        }

        return null;
    }

    @Override
    public String loadSuperPowerToPool(SuperPower superPower) {
        if (this.superPowerPool.containsKey(superPower.getName())){
            return "This super power already exists!";
        } else {
            this.superPowerPool.put(superPower.getName(), superPower);
            return String.format("%s added to pool!", superPower.getName());
        }
    }

    @Override
    public String assignSuperPowerToComicCharacter(String comicCharacter, String superPower) {
        if (this.comicCharacters.containsKey(comicCharacter)){
            if (this.comicCharacters.get(comicCharacter).containsSuperPower(superPower)){
                return String.format("%s already assigned!", superPower);
            } else {

                boolean isAnotherDoNotContainsSuperPower = true;
                for (Map.Entry<String, ComicCharacter> comicCharacterEntry : this.comicCharacters.entrySet()) {
                    if (comicCharacterEntry.getValue().containsSuperPower(superPower)){
                        isAnotherDoNotContainsSuperPower = true;
                        break;
                    }
                }

                if (isAnotherDoNotContainsSuperPower && this.superPowerPool.containsKey(superPower)){
                    this.comicCharacters.get(comicCharacter).addSuperPower(this.superPowerPool.get(superPower));
                    return String.format("%s has a new super power!", comicCharacter);
                }
            }
        }

        return null;
    }

    @Override
    public String usePowers(String characterName) {
        if (this.comicCharacters.containsKey(characterName)){
            if (this.comicCharacters.get(characterName).hasNoSuperPowers()){
                return String.format("%s has no super powers!", characterName);
            } else {
                this.comicCharacters.get(characterName).useSuperPowers();
                return String.format("%s used his super powers!", characterName);
            }

        }

        return null;
    }

    @Override
    public String startBattle(String arena) {
        if (this.arenes.containsKey(arena)){
            if (this.arenes.get(arena).isArenaEmpty()){
                return "SAFE ZONE!";
            } else {
                if (this.arenes.get(arena).startBattleIsHeroesWin()){
                    this.battlesWonHeroes++;
                    this.arenes.remove(arena);
                    return String.format("Heroes won the battle of %s!", arena);
                } else {
                    this.battlesWonAntiHeroes++;
                    this.arenes.remove(arena);
                    return String.format("Anti Heroes won the battle of %s!", arena);
                }

            }
        }

        return null;
    }

    @Override
    public String endWar() {
        if (this.battlesWonHeroes >= this.battlesWonAntiHeroes){
            return String.format("After %d battles our FRIENDLY HEROES WON!",
                    this.battlesWonHeroes + this.battlesWonAntiHeroes);
        } else {
            return "WE ARE DOOMED!";
        }
    }
}
