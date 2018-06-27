package p02_VehiclesExtension;

import java.text.DecimalFormat;

public class Truck extends Vehicle {

    public Truck(double fuel, double litersPerKm, double tankCapacity) {
        super(fuel, litersPerKm + 1.6, tankCapacity);
    }

    @Override
    protected void driven(double distance) {
        if (distance * super.getLitersPerKm() > super.getFuel()){
            throw new IllegalArgumentException("Truck needs refueling");
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

        super.setFuel(super.getFuel() + (liters * 0.95));
    }
}
