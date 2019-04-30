package entities;

import entities.factories.AirplaneWorkshop;
import entities.factories.ItemWorkshop;
import entities.factories.interfaces.AirplaneFactory;
import entities.factories.interfaces.ItemFactory;
import entities.interfaces.Airport;
import entities.interfaces.Bag;
import entities.interfaces.Passenger;
import entities.interfaces.Trip;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AirportImpl implements Airport {

    private LinkedHashMap<String, Passenger> passengers = new LinkedHashMap<>();
    private LinkedHashMap<String, Passenger> checkedPassengers = new LinkedHashMap<>();
    private LinkedHashMap<String, Trip> trips = new LinkedHashMap<>();
    private List<Bag> checkedBags = new ArrayList<>();
    private List<Bag> confiscatedBags = new ArrayList<>();

    private AirplaneFactory airplaneFactory = new AirplaneWorkshop();
    private ItemFactory itemFactory = new ItemWorkshop();

    private int idCount = 0;

    public AirportImpl() {
    }

    @Override
    public List<Bag> getCheckedInBags() {
        return this.checkedBags;
    }

    @Override
    public List<Bag> getConfiscatedBags() {
        return this.confiscatedBags;
    }

    @Override
    public List<Passenger> getPassengers() {
        return this.passengers.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<Trip> getTrips() {
        return this.trips.values().stream().collect(Collectors.toList());
    }

    @Override
    public void addPassenger(Passenger passenger) {
        passengers.put(passenger.getUsername(), passenger);
    }

    @Override
    public void addCheckedBag(Bag bag) {
        checkedBags.add(bag);
    }

    @Override
    public void addConfiscatedBag(Bag bag) {
        confiscatedBags.add(bag);
    }

    @Override
    public void addTrip(Trip trip) {
        trips.put(trip.getId(), trip);
    }

    @Override
    public Passenger getPassenger(String username) {
        return this.passengers.get(username);
    }

    @Override
    public Trip getTrip(String id) {
        return this.trips.get(id);
    }
}
