package p01_Vehicles;

import java.text.DecimalFormat;

public class Car extends Vehicle {

    public Car(double fuel, double litersPerKm) {
        super(fuel, litersPerKm + 0.9);
    }

    @Override
    protected String driven(String distanceString) {
        double distance = Double.parseDouble(distanceString);
        DecimalFormat df = new DecimalFormat("#.##");
        if (distance * super.getLitersPerKm() > super.getFuel()){
            return "Car needs refueling";
        } else {
            super.setFuel(super.getFuel()  - distance * super.getLitersPerKm());
            return String.format("Car travelled %s km", df.format(distance));
        }
    }

    @Override
    protected void refueled(double liters) {
        super.setFuel(super.getFuel() + liters);
    }


}
