package core.controllers;

import core.controllers.interfaces.AirportController;
import entities.airplanes.interfaces.FlyingMachine;
import entities.factories.AirplaneWorkshop;
import entities.factories.ItemWorkshop;
import entities.factories.interfaces.AirplaneFactory;
import entities.factories.interfaces.ItemFactory;
import entities.impl.BagImpl;
import entities.impl.PassengerImpl;
import entities.impl.TripImpl;
import entities.interfaces.Airport;
import entities.interfaces.Bag;
import entities.interfaces.Passenger;
import entities.interfaces.Trip;
import entities.items.interfaces.Item;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AirportManager implements AirportController {

    private LinkedHashMap<String, Passenger> passengers = new LinkedHashMap<>();
    private LinkedHashMap<String, Passenger> checkedPassengers = new LinkedHashMap<>();
    private LinkedHashMap<String, Trip> trips = new LinkedHashMap<>();
    private List<Bag> checkedBags = new ArrayList<>();
    private List<Bag> confiscatedBags = new ArrayList<>();

    private AirplaneFactory airplaneFactory = new AirplaneWorkshop();
    private ItemFactory itemFactory = new ItemWorkshop();

    private int idCount = 0;

    private Airport airport;

    public AirportManager(Airport airport) {
        this.airport = airport;
    }

    @Override
    public String registerPassenger(String username) throws OperationNotSupportedException {

        //If the airport already has a passenger with that username, throw an UnsupportedOperationException with the message "Passenger {username} already registered!".
        //The command adds a new passenger into the airport and returns "Registered {passengerUsername}"

        if (passengers.containsKey(username)){
            throw new OperationNotSupportedException(
                    String.format("Passenger %s already registered!", username)
            );
        }

        Passenger passenger = new PassengerImpl(username);
        passengers.put(username, passenger);
        airport.addPassenger(passenger);

        return "Registered " + username;
    }

    @Override
    public String registerBag(String username, List<String> bagItems) {

        //Gets a passenger with the provided username from the airport. Then, creates a bag with all the provided items and adds it to the passenger’s bags.
        //The command returns "Registered bag with item1, item2, itemN for {username}"

        Passenger passenger = passengers.get(username);
        List<Item> items = new ArrayList<>();
        for (String bagItem : bagItems) {
            Item item = itemFactory.createItem(bagItem);
            items.add(item);
        }

        Bag bag = new BagImpl(passenger, items);
        passenger.getBags().add(bag);
        airport.addPassenger(passenger);

        return String.format("Registered bag with %s for %s",
                String.join(", ", bagItems) , username);
    }

    @Override
    public String registerTrip(String source, String destination, String planeType) {

        //        Creates a trip with that source and destination and adds it to the airport. The Id is auto-generated from the Trip class itself.
//The command returns "Registered trip {tripId}".

        FlyingMachine flyingMachine = airplaneFactory.createAirplane(planeType);
        Trip trip = new TripImpl(source, destination, flyingMachine);

        try {
            Field id = trip.getClass().getDeclaredField("id");
            id.setAccessible(true);
            idCount++;
            String newId = String.format("%s%s%d",
                    source, destination, idCount);
            id.set(trip, newId);
        }catch (Exception ignored){
            ;
        }

        trips.put(trip.getId(), trip);
        airport.addTrip(trip);

        return "Registered trip " + trip.getId();
    }

    @Override
    public String checkIn(String username, String tripId, List<Integer> bagIndices) throws OperationNotSupportedException {
        if (checkedPassengers.containsKey(username)){
            throw new OperationNotSupportedException(
                    String.format("%s is already checked in!", username)
            );
        }

        Passenger passenger = passengers.get(username);
        checkedPassengers.put(username, passenger);

        int bagsToCheckInCount = 0;
        int confiscatedBagsCount = 0;
        List<Bag> bags = passenger.getBags();
        for (Integer bagIndice : bagIndices) {
            try {
                Bag bag = bags.get(bagIndice);
                int totalValue = bag.getItems().stream().mapToInt(Item::getValue).sum();
                if(totalValue >= 3000){
                    checkedBags.add(bag);
                    confiscatedBags.add(bag);
                    confiscatedBagsCount++;
                    bagsToCheckInCount++;
                    airport.addCheckedBag(bag);
                    airport.addConfiscatedBag(bag);
                } else {
                    checkedBags.add(bag);
                    bagsToCheckInCount++;
                    airport.addCheckedBag(bag);
                }

            }catch (Exception ignored){
                ;
            }
        }

        Trip trip = trips.get(tripId);
        trip.getFlyingMachine().getPassengers().add(passenger);

        return String.format("Checked in %s with %d/%d checked in bags",
                username, bagsToCheckInCount - confiscatedBagsCount, bagsToCheckInCount);
    }
}
