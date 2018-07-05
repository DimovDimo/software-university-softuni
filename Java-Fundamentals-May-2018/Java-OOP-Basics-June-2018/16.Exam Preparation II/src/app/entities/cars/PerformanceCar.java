package app.entities.cars;

import java.util.ArrayList;
import java.util.List;

public class PerformanceCar extends Car {

    private List<String> addOns;

    public PerformanceCar(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        this.addOns = new ArrayList<>();
    }

    @Override
    public void setHorsepower(int horsepower) {
        int increaseHorsepower = horsepower + (horsepower * 50)/100;
        super.setHorsepower(increaseHorsepower);
    }

    @Override
    public void setSuspension(int suspension) {
        int decreaseSuspension = suspension - (suspension * 25)/100;
        super.setSuspension(decreaseSuspension);
    }

    @Override
    public void tune(String addOn) {
        this.addOns.add(addOn);
    }

    @Override
    public String toString() {
        StringBuilder preformanceCar = new StringBuilder(super.toString())
                .append(System.lineSeparator());

        if (addOns.isEmpty()){
            preformanceCar.append("Add-ons: None");
        } else {
            preformanceCar.append(String.format("Add-ons: %s", String.join(", ", this.addOns)));
        }

        return preformanceCar.toString();
    }
}
