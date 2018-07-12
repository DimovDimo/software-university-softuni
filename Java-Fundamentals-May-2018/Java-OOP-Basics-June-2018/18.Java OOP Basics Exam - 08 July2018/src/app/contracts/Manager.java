package app.contracts;

public interface Manager {

    String checkComicCharacter(String characterName);

	String addHero(ComicCharacter hero);

	String addAntiHero(ComicCharacter antiHero);

	String addArena(Arena arena);

	String addHeroToArena(String arena, String hero);

	String addAntiHeroToArena(String arena, String antiHero);
	String loadSuperPowerToPool(SuperPower superPower);
	String assignSuperPowerToComicCharacter(String comicCharacter,String superPower);
    String usePowers(String characterName);

	String startBattle(String arena);

	String endWar();
}
