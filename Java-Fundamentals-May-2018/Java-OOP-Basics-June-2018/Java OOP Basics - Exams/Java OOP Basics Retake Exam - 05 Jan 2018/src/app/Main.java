package app;

import app.engines.Engine;
import app.entities.Colony.Colony;
import app.io.ConsoleInputReader;
import app.io.ConsoleOutputWriter;
import app.utilities.InputParser;

public class Main {
    public static void main(String[] args) {
        ConsoleInputReader inputReader = new ConsoleInputReader();
        ConsoleOutputWriter outputWriter = new ConsoleOutputWriter();
        InputParser inputParser = new InputParser();

        int[] tokens = inputParser.parseInput(inputReader.readLine()).stream()
                .mapToInt(Integer::parseInt)
                .toArray();
        int maxFamilyCount = tokens[0];
        int maxFamilyCapacity = tokens[1];
        Colony colony = new Colony(maxFamilyCount, maxFamilyCapacity);

        Engine engine = new Engine(inputReader, outputWriter, inputParser, colony);

        engine.run();
    }
}
