package core;

import contracts.BoatSimulatorController;
import contracts.Race;
import database.BoatSimulatorDatabase;
import exeptions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Engine {
    private CommandHandler commandHandler;

    public Engine(CommandHandler commandHandler)
    {
        this.commandHandler = commandHandler;
    }

//    public Engine()
//    {
//        this.commandHandler = new CommandHandler();
//    }

//    public contracts.CommandHandler getCommandHandler;

    public void run()
    {
        while (true)
        {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            String name;
            List<String> parameters;

            if (line.equals("End")) {
                break;
            }

            List<String> tokens = Arrays.asList(line.split("\\\\"));
            name = tokens.get(0);
            parameters = tokens.stream().skip(1).collect(Collectors.toList());

            try
            {
                String commandResult = this.commandHandler.executeCommand(name, parameters);
                System.out.println(commandResult);
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }

            line = scanner.nextLine();
        }
    }

//    public static void main(String[] args) {
//        BoatSimulatorControllerImpl ctrl = new BoatSimulatorControllerImpl() {
//            @Override
//            public Race getCurrentRace() {
//                return null;
//            }
//
//            @Override
//            public BoatSimulatorDatabase getDatabase() {
//                return null;
//            }
//
//            @Override
//            public String createBoatEngine(String model, int horsepower, int displacement, String engineType) {
//                return null;
//            }
//
//            @Override
//            public String createRowBoat(String model, int weight, int oars) throws DuplicateModelException {
//                return null;
//            }
//
//            @Override
//            public String createSailBoat(String model, int weight, int sailEfficiency) throws DuplicateModelException {
//                return null;
//            }
//
//            @Override
//            public String createPowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws NonExistentModelException, DuplicateModelException {
//                return null;
//            }
//
//            @Override
//            public String createYacht(String model, int weight, String engineModel, int cargoWeight) throws
//                    NonExistentModelException, DuplicateModelException {
//                return null;
//            }
//
//            @Override
//            public String openRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws RaceAlreadyExistsException {
//                return null;
//            }
//
//            @Override
//            public String signUpBoat(String model) throws NonExistentModelException, DuplicateModelException, NoSetRaceException {
//                return null;
//            }
//
//            @Override
//            public String startRace() throws InsufficientContestantsException, NoSetRaceException {
//                return null;
//            }
//
//            @Override
//            public String getStatistic() {
//                return null;
//            }
//        };
//
//        CommandHandler commandHandler = new CommandHandler(ctrl);
//        Engine engine = new Engine();
//        engine.run();
//    }
}
