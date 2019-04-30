package entities.airplanes;

import entities.airplanes.interfaces.FlyingMachine;
import entities.interfaces.Bag;
import entities.interfaces.Passenger;

import java.util.ArrayList;
import java.util.List;

public abstract class Airplane implements FlyingMachine {

    //An abstract airplane has the following characteristics:
    //int  seats
    //int  bagsCount
    //List  bags – the carry-on bags in the plane
    //List  passengers
    //boolean  isOverbooked – returns true if the passengers count is greater than the seats (calculated each time the method is called).

    private int seats;
    private int bagsCount;
    private List<Bag> bags;
    private List<Passenger> passengers;
    private boolean isOverbooked;

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
        this.isOverbooked = this.passengers.size() > this.seats;
        return this.isOverbooked;
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
