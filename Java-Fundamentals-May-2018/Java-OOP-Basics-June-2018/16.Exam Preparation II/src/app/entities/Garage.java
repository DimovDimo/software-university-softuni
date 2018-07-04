package app.entities;

import app.contracts.Car;

import java.util.ArrayList;
import java.util.Collection;

public class Garage {
    private Collection<Car> parkedCars;

    public Garage() {
        this.parkedCars = new ArrayList<>();
    }
}
