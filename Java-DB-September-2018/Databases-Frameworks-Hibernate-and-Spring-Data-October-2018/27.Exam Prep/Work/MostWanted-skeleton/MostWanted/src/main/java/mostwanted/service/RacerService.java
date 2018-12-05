package mostwanted.service;

public interface RacerService {

    Boolean racersAreImported();

    String readRacersJsonFile();

    String importRacers(String racersFileContent);

    String exportRacingCars();
}
