package mostwanted.service;

public interface RaceEntryService {

    Boolean raceEntriesAreImported();

    String readRaceEntriesXmlFile();

    String importRaceEntries();
}
