import core.EngineController;
import core.controllers.AirportManager;
import core.controllers.FlightManager;
import core.controllers.interfaces.AirportController;
import core.controllers.interfaces.FlightController;
import core.interfaces.Engine;
import core.io.ConsoleReader;
import core.io.ConsoleWriter;
import core.io.interfacese.Reader;
import core.io.interfacese.Writer;
import entities.AirportImpl;
import entities.interfaces.Airport;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();

        Airport airport = new AirportImpl();
        AirportController airportController = new AirportManager(airport);
        FlightController flightController = new FlightManager(airport);

        Engine engine = new EngineController(reader, writer, airportController, flightController);

        engine.run();
    }
}
