package app.contracts;

import java.util.Collection;

public interface Arena {

    String getArenaName();

	boolean isArenaFull();

	void addHero(ComicCharacter hero);

	void addAntiHero(ComicCharacter antiHero);

	boolean fightHeroes();

    boolean containsHero(ComicCharacter hero);

	boolean containsAntiHero(ComicCharacter antiHero);

	boolean isArenaEmpty();

	boolean startBattleIsHeroesWin();

	void removeDeadMembers(Collection<ComicCharacter> firstTeam, Collection<ComicCharacter> secondTeam);

	boolean areHeroesMoreOrEqual();

	boolean areAllHeroesDead();

	boolean areAllAntiHeroesDead();
}
