package app;

import app.core.CarManager;
import app.engines.Engine;
import app.io.ConsoleInputReader;
import app.io.ConsoleOutputWriter;
import app.utilities.InputParser;

public class Main {
    public static void main(String[] args) {
        ConsoleInputReader inputReader = new ConsoleInputReader();
        ConsoleOutputWriter outputWriter = new ConsoleOutputWriter();
        InputParser inputParser = new InputParser();
        CarManager carManagerImpl = new CarManager();
        Engine engine = new Engine(inputReader, outputWriter, inputParser, carManagerImpl);

        engine.run();
    }
}
