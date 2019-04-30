package entities.factories.interfaces;

import entities.airplanes.interfaces.FlyingMachine;

public interface AirplaneFactory {
    FlyingMachine createAirplane(String type);
}

