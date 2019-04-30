package core.controllers;

import core.controllers.interfaces.FlightController;
import entities.AirportImpl;
import entities.interfaces.Airport;
import entities.interfaces.Bag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

public class FlightManagerTest {

    private FlightController flightController;
    private Airport airport;

    @Before
    public void setUp() throws Exception {
        airport = new AirportImpl();
        flightController = new FlightManager(airport);
    }

    @Test
    public void empty() {
        String expected = "Confiscated bags: 0 (0 items) => $0";
        String actual = flightController.takeOff();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void oneCnfBg() {
        Bag bag = Mockito.mock(Bag.class);
        Mockito.when(bag.getItems()).thenReturn(Collections.emptyList());
        airport.addConfiscatedBag(bag);

        String expected = "Confiscated bags: 1 (0 items) => $0";
        String actual = flightController.takeOff();

        Assert.assertEquals(expected, actual);
    }
}
