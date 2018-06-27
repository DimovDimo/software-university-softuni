package p02_VehiclesExtension;

public abstract class Vehicle {
    private double fuel;
    private double litersPerKm;
    private double tankCapacity;

    public Vehicle(double fuel, double litersPerKm, double tankCapacity) {
        this.setFuel(fuel);
        this.setLitersPerKm(litersPerKm);
        this.setTankCapacity(tankCapacity);
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    protected void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
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

    protected abstract void driven(double distance);

    protected abstract void refueled(double liters);
}
