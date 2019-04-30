package core.controllers.interfaces;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface AirportController {
    String registerPassenger(String username) throws OperationNotSupportedException;

    String registerBag(String username, List<String> bagItems);

    String registerTrip(String source, String destination, String planeType);

    String checkIn(String username, String tripId, List<Integer> bagIndices);
}
