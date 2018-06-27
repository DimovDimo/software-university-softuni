package p01_Vehicles;

public abstract class Vehicle {
    private double fuel;
    private double litersPerKm;

    public Vehicle(double fuel, double litersPerKm) {
        this.setFuel(fuel);
        this.setLitersPerKm(litersPerKm);
    }

    public double getFuel() {
        return fuel;
    }

    protected void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public double getLitersPerKm() {
        return litersPerKm;
    }

    protected void setLitersPerKm(double litersPerKm) {
        this.litersPerKm = litersPerKm;
    }

    protected abstract String driven(String distance);

    protected abstract void refueled(double liters);
}
