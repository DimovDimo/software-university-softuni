package core.controllers;

import core.controllers.interfaces.AirportController;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public class AirportManager implements AirportController {
    @Override
    public String registerPassenger(String username) throws OperationNotSupportedException {
        return null;
    }

    @Override
    public String registerBag(String username, List<String> bagItems) {
        return null;
    }

    @Override
    public String registerTrip(String source, String destination, String planeType) {
        return null;
    }

    @Override
    public String checkIn(String username, String tripId, List<Integer> bagIndices) {
        return null;
    }
}
