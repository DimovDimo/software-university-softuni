package p01_Vehicles;

import java.text.DecimalFormat;

public class Truck extends Vehicle {

    public Truck(double fuel, double litersPerKm) {
        super(fuel, litersPerKm + 1.6);
    }

    @Override
    protected String driven(String distanceString) {
        double distance = Double.parseDouble(distanceString);
        DecimalFormat df = new DecimalFormat("#.##");
        if (distance * super.getLitersPerKm() > super.getFuel()){
            return "Truck needs refueling";
        } else {
            super.setFuel(super.getFuel()  - distance * super.getLitersPerKm());
            return String.format("Truck travelled %s km", df.format(distance));
        }
    }

    @Override
    protected void refueled(double liters) {
        super.setFuel(super.getFuel() + (liters * 0.95));
    }
}
