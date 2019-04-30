package entities.impl;

import entities.interfaces.Bag;
import entities.interfaces.Passenger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PassengerImpl implements Passenger {

    //A passenger has the following characteristics:
    //String  username
    //List<Bag>  bags

    private String username;
    private List<Bag> bags;

    public PassengerImpl(String username) {
        this.username = username;
        this.bags = new ArrayList<>();;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public List<Bag> getBags() {
//        return Collections.unmodifiableList(this.bags);
        return this.bags;
    }
}
