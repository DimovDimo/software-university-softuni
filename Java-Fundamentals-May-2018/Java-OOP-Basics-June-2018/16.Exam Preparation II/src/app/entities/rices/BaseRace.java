package app.entities.rices;

import app.contracts.Car;
import app.contracts.Rice;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseRace implements Rice {

    private int lenght;
    private String route;
    private int prizePool;
    private Collection<Car> participants;

    public BaseRace(int lenght, String route, int prizePool) {
        this.lenght = lenght;
        this.route = route;
        this.prizePool = prizePool;
        this.participants = new ArrayList<>();
    }
}
