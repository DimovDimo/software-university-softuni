import core.EngineController;
import core.interfaces.Engine;
import core.io.ConsoleReader;
import core.io.ConsoleWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ConsoleReader reader = new ConsoleReader();

        ConsoleWriter writer = new ConsoleWriter();


        Engine engine = new EngineController(reader, writer, null, null);

        engine.run();
    }
}
