package p02_VehiclesExtension;

import java.text.DecimalFormat;

public class Car extends Vehicle {

    public Car(double fuel, double litersPerKm, double tankCapacity) {
        super(fuel, litersPerKm + 0.9, tankCapacity);
    }

    @Override
    protected void driven(double distance) {
        if (distance * super.getLitersPerKm() > super.getFuel()){
            throw new IllegalArgumentException("Car needs refueling");
        }

        super.setFuel(super.getFuel()  - distance * super.getLitersPerKm());
    }

    @Override
    protected void refueled(double liters) {
        if (liters <= 0){
            throw new IllegalArgumentException("Fuel must be a positive number");
        } else if (super.getFuel() + liters > super.getTankCapacity()){
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }

        super.setFuel(super.getFuel() + liters);
    }


}
