package entities;

import entities.interfaces.Airport;
import entities.interfaces.Bag;
import entities.interfaces.Passenger;
import entities.interfaces.Trip;

import java.util.List;

public class AirportImpl implements Airport {
    @Override
    public List<Bag> getCheckedInBags() {
        return null;
    }

    @Override
    public List<Bag> getConfiscatedBags() {
        return null;
    }

    @Override
    public List<Passenger> getPassengers() {
        return null;
    }

    @Override
    public List<Trip> getTrips() {
        return null;
    }

    @Override
    public void addPassenger(Passenger passenger) {

    }

    @Override
    public void addCheckedBag(Bag bag) {

    }

    @Override
    public void addConfiscatedBag(Bag bag) {

    }

    @Override
    public void addTrip(Trip trip) {

    }

    @Override
    public Passenger getPassenger(String username) {
        return null;
    }

    @Override
    public Trip getTrip(String id) {
        return null;
    }
}
