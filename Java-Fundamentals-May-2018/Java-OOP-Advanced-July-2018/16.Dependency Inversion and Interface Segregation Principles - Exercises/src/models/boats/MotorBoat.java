package models.boats;

import contracts.Modelable;
import contracts.Race;
import models.engines.JetEngine;
import models.engines.SterndriveEngine;
import utility.Constants;
import utility.Validator;

import java.util.List;

public final class MotorBoat {

    private int cargoWeight;
    private List<JetEngine> jetEngines;
    private List<SterndriveEngine> sterndriveEngines;
    public Boolean isSailboat;

    public MotorBoat(String model, int weight, int sailEfficiency, int oars, int cargoWeight, List<JetEngine> jetEngines, List<SterndriveEngine> sterndriveEngines, Boolean isSailboat) {

        this.cargoWeight = cargoWeight;
        this.jetEngines = jetEngines;
        this.sterndriveEngines = sterndriveEngines;
        this.isSailboat = isSailboat;
    }

//    @Override
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        Validator.validateModelLength(model, Constants.MIN_BOAT_MODEL_LENGTH);
//        this.model = model;
//    }

//    public int getWeight() {
//        return weight;
//    }
//
//    public void setWeight(int weight) {
//        Validator.validatePropertyValue(weight, "Weight");
//        this.weight = weight;
//    }

//    public int getOars() {
//        return oars;
//    }
//
//    public void setOars(int oars) {
//        Validator.validatePropertyValue(oars, "Oars");
//        this.oars = oars;
//    }

//    public int getSailEfficiency() {
//        return sailEfficiency;
//    }
//
//    public void setSailEfficiency(int sailEfficiency) {
//        if (sailEfficiency < 1 || sailEfficiency > 100) {
//            throw new IllegalArgumentException(Constants.INCORRECT_SAIL_EFFICIENCY_MESSAGE);
//        }
//        this.sailEfficiency = sailEfficiency;
//    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(int cargoWeight) {
        Validator.validatePropertyValue(cargoWeight, "Cargo Weight");
        this.cargoWeight = cargoWeight;
    }

    public List<JetEngine> getJetEngines() {
        return jetEngines;
    }

    public void setJetEngines(List<JetEngine> jetEngines) {
        this.jetEngines = jetEngines;
    }

    public List<SterndriveEngine> getSterndriveEngines() {
        return sterndriveEngines;
    }

    public void setSterndriveEngines(List<SterndriveEngine> sterndriveEngines) {
        this.sterndriveEngines = sterndriveEngines;
    }

    public double CalculateRaceSpeed(Race race) {
        //if (this.getJetEngines().size() + this.getSterndriveEngines().size() == 2) {
        //    var speed = this.JetEngines.Sum(x = > x.Output)+this.SterndriveEngines.Sum(x = > x.Output)
        //    -this.Weight + (race.OceanCurrentSpeed / 5d);
        //    return speed;
        //} else if (this.getJetEngines().size() + this.getSterndriveEngines().size() == 1) {
        //    var speed = this.JetEngines.Sum(x = > x.Output)+this.SterndriveEngines.Sum(x = > x.Output)
        //    -this.Weight - this.CargoWeight + (race.OceanCurrentSpeed / 2d);
        //    return speed;
        //} else if (isSailboat) {
        //    var speed = (race.WindSpeed * (this.SailEfficiency / 100d)) - this.Weight + (race.OceanCurrentSpeed / 2d);
        //    return speed;
        //} else {
        //    var speed = (this.Oars * 100) - this.Weight + race.OceanCurrentSpeed;
        //    return speed;
        //}
        return 0;
    }
}


