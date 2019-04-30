package entities.impl;

import entities.airplanes.interfaces.FlyingMachine;
import entities.interfaces.Trip;

public class TripImpl implements Trip {

    //A trip has the following characteristics:
    //Sring  id – The combination of the source and destination cities, plus an ever-increasing integer (starting at 1)
    //String  source
    //String  destination
    //boolean  isCompleted
    //FlyingMachine  airplane

    private String id;
    private String source;
    private String destination;
    private boolean isCompleted;
    private FlyingMachine airplane;

    public TripImpl(String source, String destination, FlyingMachine airplane) {
//        this.id = id;
        this.source = source;
        this.destination = destination;
//        this.isCompleted = isCompleted;
        this.airplane = airplane;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getSource() {
        return this.source;
    }

    @Override
    public String getDestination() {
        return this.destination;
    }

    @Override
    public boolean isCompleted() {
        return this.isCompleted;
    }

    @Override
    public FlyingMachine getFlyingMachine() {
        return this.airplane;
    }

    @Override
    public void complete() {
        this.isCompleted = true;
    }
}
