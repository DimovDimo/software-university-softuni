package controllers;

import utility.Constants;
import contracts.Race;
import database.BoatSimulatorDatabase;
import exeptions.*;
import models.engines.JetEngine;
import models.boats.MotorBoat;
import models.engines.SterndriveEngine;

import java.util.*;

public class BoatSimulatorControllerImpl implements contracts.BoatSimulatorController {

    private Map<Double, MotorBoat> map;

    private BoatSimulatorDatabase database;

    private Race currentRace;

    public BoatSimulatorControllerImpl(BoatSimulatorDatabase database, Race currentRace) {
        this.database = database;
        this.currentRace = currentRace;
        this.map = new LinkedHashMap<>();
    }

//    public BoatSimulatorControllerImpl() {
//        this.setDatabase(new BoatSimulatorDatabase());
//        this.setCurrentRace(null);
//    }

//    @Override
//    public BoatSimulatorDatabase getDatabase() {
//        return this.database;
//    }

//    public void setDatabase(BoatSimulatorDatabase database) {
//        this.database = database;
//    }

//    @Override
//    public Race getCurrentRace() {
//        return this.currentRace;
//    }

//    public void setCurrentRace(Race currentRace) {
//        this.currentRace = currentRace;
//    }

    private String isFinished(Double key) {
        if (key == Double.NEGATIVE_INFINITY) {
            return "Did not finish!";
        }
        return String.format("%f.2 sec", key);
    }

    private void findFastest(List<MotorBoat> participants) {
        Double bestTime = 0.0;
        MotorBoat winner = null;
        for (MotorBoat participant : participants) {
            Double speed = participant.CalculateRaceSpeed(this.currentRace);
            Double time = this.currentRace.getDistance() / speed;
            if (time < bestTime) {
                bestTime = time;
                winner = participant;
            }
        }

        map.put(bestTime, winner);
        participants.remove(winner);
    }

    private void validateRaceIsSet() throws NoSetRaceException {
        if (this.currentRace == null) {
            throw new NoSetRaceException(Constants.NO_SET_RACE_MESSAGE);
        }
    }

    private void validateRaceIsEmpty() throws RaceAlreadyExistsException {
        if (this.currentRace != null) {
            throw new RaceAlreadyExistsException(Constants.RACE_ALREADY_EXISTS_MESSAGE);
        }
    }

    @Override
    public String createBoatEngine(String model, int horsepower, int displacement, String engineType) {
        return null;
    }

//    public String CreateBoatEngine(String model, int horsepower, int displacement, EngineType engineType) throws DuplicateModelException {
//        Modelable engine;
//        switch (engineType) {
//            case JET:
//                engine = new JetEngine(model, horsepower, displacement);
//                break;
//            case STERNDRIVE:
//                engine = new SterndriveEngine(model, horsepower, displacement);
//                break;
//            default:
//                throw new NotImplementedException();
//        }
//
//        this.database.getEngines().add(engine);
//        return String.format(
//                "Engine model %s with %s HP and displacement %s cm3 created successfully.",
//                model,
//                horsepower,
//                displacement);
//    }

    @Override
    public String createRowBoat(String model, int weight, int oars) throws DuplicateModelException {
        MotorBoat boat = new MotorBoat(model, weight, 1, oars, 1, new ArrayList<>(), new ArrayList<>(), false);
        this.database.getBoats().add(boat);
        return String.format("Row boat with model %s registered successfully.", model);
    }

    @Override
    public String createSailBoat(String model, int weight, int sailEfficiency) throws DuplicateModelException {
        MotorBoat boat = new MotorBoat(model, weight, sailEfficiency, 1, 1, new ArrayList<>(), new ArrayList<>(), true);
        this.database.getBoats().add(boat);
        return String.format("Sail boat with model %s registered successfully.", model);
    }

    @Override
    public String createPowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws NonExistentModelException, DuplicateModelException {
        JetEngine firstEngine = (JetEngine) this.database.getEngines().getItem(firstEngineModel);
        SterndriveEngine secondEngine = (SterndriveEngine) this.database.getEngines().getItem(secondEngineModel);
        MotorBoat boat = new MotorBoat(model, weight, 1, 1, 1, Arrays.asList(firstEngine), Arrays.asList(secondEngine), false);
        this.database.getBoats().add(boat);
        return String.format("Power boat with model %s registered successfully.", model);
    }

    @Override
    public String createYacht(String model, int weight, String engineModel, int cargoWeight) throws NonExistentModelException, DuplicateModelException {
        JetEngine engine = (JetEngine) this.database.getEngines().getItem(engineModel);
        MotorBoat boat = new MotorBoat(model, weight, 1, 1, cargoWeight, Arrays.asList(engine), new ArrayList<>(), false);
        this.database.getBoats().add(boat);
        return String.format("Yacht with model %s registered successfully.", model);
    }

    @Override
    public String openRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws RaceAlreadyExistsException {
        Race race = new models.Race(distance, windSpeed, oceanCurrentSpeed, allowsMotorboats);
        this.validateRaceIsEmpty();
        this.currentRace = race;
        return
                String.format(
                        "A new race with distance %s meters, wind speed %sm/s and ocean current speed %s m/s has been set.",
                        distance, windSpeed, oceanCurrentSpeed);
    }

    @Override
    public String signUpBoat(String model) throws NonExistentModelException, DuplicateModelException, NoSetRaceException {
        MotorBoat boat = this.database.getBoats().getItem(model);
        this.validateRaceIsSet();
        if (!this.currentRace.getAllowsMotorboats() && boat instanceof MotorBoat) {
            throw new IllegalArgumentException(Constants.INCORRECT_BOAT_TYPE_MESSAGE);
        }
        this.currentRace.AddParticipant(boat);
        return String.format("Boat with model %s has signed up for the current Race.", model);
    }

    @Override
    public String startRace() throws InsufficientContestantsException, NoSetRaceException {
        this.validateRaceIsSet();
        List<MotorBoat> participants = this.currentRace.GetParticipants();
        if (participants.size() < 3) {
            throw new InsufficientContestantsException(Constants.INSUFFICIENT_CONTESTANTS_MESSAGE);
        }


        for (int i = 0; i < 3; i++) {
            findFastest(participants);
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Double, MotorBoat> doubleMotorBoatEntry : map.entrySet()) {
            result.append(String.format("First place: %s Model: %s Time: %s",
                    doubleMotorBoatEntry.getValue().getClass().getSimpleName().toString(),
                    doubleMotorBoatEntry.getValue().getModel(),
                    isFinished(doubleMotorBoatEntry.getKey())));
        }

        this.currentRace = null;

        return result.toString();
    }

    @Override
    public String getStatistic() {
        return null;
    }

//    public String getStatistic() {
//        //TODO Bonus Task Implement me
//        throw new NotImplementedException();
//    }

}
