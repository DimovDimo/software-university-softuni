package core;

import contracts.BoatSimulatorController;
import utility.Constants;
import exeptions.*;

import java.util.List;

public class CommandHandler implements contracts.CommandHandler {
    private BoatSimulatorController controller;

    public CommandHandler(BoatSimulatorController controller) {
        this.controller = controller;
    }

//    public CommandHandler() {
//        this.setContrller(new controllers.BoatSimulatorControllerImpl());
//    }
//
//    public BoatSimulatorControllerImpl getController() {
//        return this.controller;
//    }

//    private void setContrller(BoatSimulatorControllerImpl controller) {
//        this.controller = controller;
//    }

    public String executeCommand(String name, List<String> parameters) throws DuplicateModelException, NonExistentModelException, RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException {
        switch (name) {
            case "createBoatEngine":
//                EngineType engineType;
                if (parameters.get(3).equals("JET")) {
                    return this.controller.createBoatEngine(
                            parameters.get(0),
                            Integer.parseInt(parameters.get(1)),
                            Integer.parseInt(parameters.get(2)),
                            "JET");
                } else if (parameters.get(3).equals("STERNDRIVE")) {
                    return this.controller.createBoatEngine(
                            parameters.get(0),
                            Integer.parseInt(parameters.get(1)),
                            Integer.parseInt(parameters.get(2)),
                            "STERNDRIVE");
                }

                throw new IllegalArgumentException(Constants.INCORRECT_ENGINE_TYPE_MESSAGE);

            case "createRowBoat":
                return this.controller.createRowBoat(
                        parameters.get(0),
                        Integer.parseInt(parameters.get(1)),
                        Integer.parseInt(parameters.get(2)));
            case "createSailBoat":
                return this.controller.createSailBoat(
                        parameters.get(0),
                        Integer.parseInt(parameters.get(1)),
                        Integer.parseInt(parameters.get(2)));
            case "createPowerBoat":
                return this.controller.createPowerBoat(
                        parameters.get(0),
                        Integer.parseInt(parameters.get(1)),
                        parameters.get(2),
                        parameters.get(3));
            case "createYacht":
                return this.controller.createYacht(
                        parameters.get(0),
                        Integer.parseInt(parameters.get(1)),
                        parameters.get(2),
                        Integer.parseInt(parameters.get(3)));
            case "openRace":
                return this.controller.openRace(
                        Integer.parseInt(parameters.get(0)),
                        Integer.parseInt(parameters.get(1)),
                        Integer.parseInt(parameters.get(2)),
                        Boolean.parseBoolean(parameters.get(3)));
            case "signUpBoat":
                return this.controller.signUpBoat(parameters.get(0));
            case "startRace":
                return this.controller.startRace();
            case "getStatistic":
                return this.controller.getStatistic();
            default:
                throw new IllegalArgumentException();
        }
    }
}
