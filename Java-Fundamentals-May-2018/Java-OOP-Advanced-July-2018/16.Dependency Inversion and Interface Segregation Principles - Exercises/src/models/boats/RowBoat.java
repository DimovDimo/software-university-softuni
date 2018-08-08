package models.boats;

import utility.Validator;

public class RowBoat extends BaseBoat {

    private int oars;

    public RowBoat(String model, int weight, int oars) {
        super(model, weight);
        this.setOars(oars);
    }

    public int getOars() {
        return oars;
    }

    public void setOars(int oars) {
        Validator.validatePropertyValue(oars, "Oars");
        this.oars = oars;
    }
}
