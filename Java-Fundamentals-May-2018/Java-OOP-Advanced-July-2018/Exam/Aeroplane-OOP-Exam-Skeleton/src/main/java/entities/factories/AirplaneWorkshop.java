package entities.factories;

import entities.airplanes.interfaces.FlyingMachine;
import entities.factories.interfaces.AirplaneFactory;

public class AirplaneWorkshop implements AirplaneFactory {
    @Override
    public FlyingMachine createAirplane(String type) {
        return null;
    }
}
