package mostwanted.service;

public interface RaceService {

    Boolean racesAreImported();

    String readRacesXmlFile();

    String importRaces();
}
