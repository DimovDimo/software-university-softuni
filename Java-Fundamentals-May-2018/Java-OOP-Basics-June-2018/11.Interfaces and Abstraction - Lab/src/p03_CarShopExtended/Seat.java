package p03_CarShopExtended;

import java.io.Serializable;

public class Seat implements Sellable, Serializable {
    private final String countryProduced;
    private final String model;
    private final String color;
    private final Integer horsePower;
    private final Double price;

    public Seat(String model, String color, Integer horsePower, String countryProduced, Double price) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.countryProduced = countryProduced;
        this.price = price;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires", this.getModel(), this.countryProduced, TIRES);
    }

    @Override
    public Double getPrice() {
        return this.price;
    }
}
