package models.boats;

import contracts.Boat;
import utility.Constants;
import utility.Validator;

public abstract class BaseBoat implements Boat {

    private String model;

    private int weight;

    public BaseBoat(String model, int weight) {
        this.setModel(model);
        this.setWeight(weight);
    }

    public void setModel(String model) {
        Validator.validateModelLength(model, Constants.MIN_BOAT_MODEL_LENGTH);
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setWeight(int weight) {
        Validator.validatePropertyValue(weight, "Weight");
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
