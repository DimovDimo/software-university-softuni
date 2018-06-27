package p02_VehiclesExtension;

public class Bus extends Vehicle {

    public Bus(double fuel, double litersPerKm, double tankCapacity) {
        super(fuel, litersPerKm + 1.4, tankCapacity);
    }

    public void drivenEmpty(double distance){
        if (distance * super.getLitersPerKm() > super.getFuel()){
            throw new IllegalArgumentException("Bus needs refueling");
        }

        super.setFuel(super.getFuel()  - distance * (super.getLitersPerKm() - 1.4));
    }

    @Override
    protected void driven(double distance) {
        if (distance * super.getLitersPerKm() > super.getFuel()){
            throw new IllegalArgumentException("Bus needs refueling");
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
