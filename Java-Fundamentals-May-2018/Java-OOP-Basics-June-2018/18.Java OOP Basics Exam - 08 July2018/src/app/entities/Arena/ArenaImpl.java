package app.entities.Arena;

import app.contracts.Arena;
import app.contracts.ComicCharacter;
import app.entities.ComicCharacter.AntiHero.AntiHero;
import app.entities.ComicCharacter.Hero.Hero;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArenaImpl implements Arena {

    private String arenaName;
    private List<ComicCharacter> heroes;
    private List<ComicCharacter> antiHeroes;
    private int capacity;

    public ArenaImpl(String arenaName, int capacity) {
        this.setArenaName(arenaName);
        this.setHeroes();
        this.setAntiHeroes();
        this.setCapacity(capacity);
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public void setHeroes() {
        this.heroes = new ArrayList<>();
    }

    public void setAntiHeroes() {
        this.antiHeroes = new ArrayList<>();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String getArenaName() {
        return this.arenaName;
    }

    @Override
    public boolean isArenaFull() {
        if (this.heroes.size() + this.antiHeroes.size() >= this.capacity){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addHero(ComicCharacter hero) {
        this.heroes.add(hero);
    }

    @Override
    public void addAntiHero(ComicCharacter antiHero) {
        this.antiHeroes.add(antiHero);
    }

    @Override
    public boolean fightHeroes() {
        return false;
    }

    @Override
    public boolean containsHero(ComicCharacter hero){
        return this.heroes.contains(hero);
    }

    @Override
    public boolean containsAntiHero(ComicCharacter antiHero){
        return this.antiHeroes.contains(antiHero);
    }

    @Override
    public boolean isArenaEmpty(){
        if (this.heroes.size() == 0 && this.antiHeroes.size() == 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean startBattleIsHeroesWin(){
        Collection<ComicCharacter> firstTeam;
        Collection<ComicCharacter> secondTeam;
        if (areHeroesMoreOrEqual()){
            firstTeam = this.heroes;
            secondTeam = this.antiHeroes;
        } else {
            firstTeam = this.antiHeroes;
            secondTeam = this.heroes;
        }

        boolean isFirstTeamAttak = true;
        while (true){
            if (areAllHeroesDead()){
                return false;
            } else if(areAllAntiHeroesDead()) {
                return true;
            }

            int minSize = Math.min(firstTeam.size(), secondTeam.size());
            for (int teamIndex = 0; teamIndex < minSize; teamIndex++) {
                if (isFirstTeamAttak){
                    ((List<ComicCharacter>) secondTeam).get(teamIndex)
                            .takeDamage((
                                    (List<ComicCharacter>) firstTeam).get(teamIndex).attack());
                } else {
                    ((List<ComicCharacter>) firstTeam).get(teamIndex)
                            .takeDamage((
                                    (List<ComicCharacter>) secondTeam).get(teamIndex).attack());
                }
            }

            removeDeadMembers(firstTeam, secondTeam);
            isFirstTeamAttak = !isFirstTeamAttak;
        }
    }

    @Override
    public void removeDeadMembers(Collection<ComicCharacter> firstTeam, Collection<ComicCharacter> secondTeam) {
        firstTeam.removeIf(member -> member.getHealth() <= 0);
        secondTeam.removeIf(member -> member.getHealth() <= 0);
    }

    @Override
    public boolean areHeroesMoreOrEqual() {
        if (this.heroes.size() >= this.antiHeroes.size()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean areAllHeroesDead(){
        boolean areAllHeroesDead = true;

        for (ComicCharacter hero : this.heroes) {
            if (hero.getHealth() >= 0){
                areAllHeroesDead = false;
                break;
            }
        }

        if (areAllHeroesDead){
            return true;
        }

        return false;
    }

    @Override
    public boolean areAllAntiHeroesDead(){
        boolean areAllAntiHeroesDead = true;

        for (ComicCharacter antiHero : this.antiHeroes) {
            if (antiHero.getHealth() >= 0){
                areAllAntiHeroesDead = false;
                break;
            }
        }

        if (areAllAntiHeroesDead){
            return true;
        }

        return false;
    }
}
