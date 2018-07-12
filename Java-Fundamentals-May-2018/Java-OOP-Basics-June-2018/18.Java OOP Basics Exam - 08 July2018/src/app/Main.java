package app;

import app.contracts.Manager;
import app.core.WarManager;
import app.engines.Engine;
import app.io.ConsoleInputReader;
import app.io.ConsoleOutputWriter;
import app.utilities.InputParser;

public class Main {
    public static void main(String[] args) {
        ConsoleInputReader inputReader = new ConsoleInputReader();
        ConsoleOutputWriter outputWriter = new ConsoleOutputWriter();
        InputParser inputParser = new InputParser();
        Manager warManager = new WarManager();
        Engine engine = new Engine(inputReader, outputWriter, inputParser, warManager);

        engine.run();
    }
}
