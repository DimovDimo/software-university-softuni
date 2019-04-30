package entities.airplanes;

import entities.airplanes.interfaces.FlyingMachine;
import entities.interfaces.Bag;
import entities.interfaces.Passenger;

import java.util.ArrayList;
import java.util.List;

public abstract class Airplane implements FlyingMachine {
    private int seats;
    private int bagsCount;
    private List<Bag> bags;
    private List<Passenger> passengers;

    protected Airplane(int seats, int bagsCount) {
        this.passengers = new ArrayList<>();
        this.seats = seats;
        this.bagsCount = bagsCount;
        this.bags = new ArrayList<>();
    }

    @Override
    public int getBaggageCompartments() {
        return this.bagsCount;
    }

    @Override
    public List<Bag> getBaggageCompartment() {
        return this.bags;
    }

    @Override
    public boolean isOverbooked() {
        return this.passengers.size() > this.seats;
    }

    @Override
    public List<Passenger> getPassengers() {
        return this.passengers;
    }

    @Override
    public int getSeats() {
        return this.seats;
    }

    @Override
    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    @Override
    public Passenger removePassenger(int seat) {
        return this.passengers.remove(seat);
    }

    @Override
    public List<Bag> ejectPassengerBags(Passenger passenger) {
        return passenger.getBags();
    }

    @Override
    public void loadBag(Bag bag) {
        this.bags.add(bag);
    }
}
