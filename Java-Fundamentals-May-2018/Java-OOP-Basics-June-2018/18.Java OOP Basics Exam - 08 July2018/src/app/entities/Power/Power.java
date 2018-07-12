package app.entities.Power;

import app.contracts.SuperPower;

public class Power implements SuperPower {

    private String name;
    private double powerPoints;

    public Power(String name, double powerPoints) {
        this.setName(name);
        this.setPowerPoints(powerPoints);
    }

    public void setName(String name) {
        String nameTest = name.replaceAll("@", " ").trim();
        if (nameTest.matches("([A-Za-z_]{3,})") == false &&
                name.startsWith("@") &&
                name.endsWith("@")){
            throw new IllegalArgumentException("Super power name not in the correct format!");
        }

        this.name = name;
    }

    public void setPowerPoints(double powerPoints) {
        if (powerPoints <= 0){
            throw new IllegalArgumentException("Power points should be a possitive number!");
        }

        this.powerPoints = powerPoints;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPowerPoints() {
        return this.powerPoints + this.getName().length() / 2;
    }

    @Override
    public String toString() {
        return null;
    }
}
