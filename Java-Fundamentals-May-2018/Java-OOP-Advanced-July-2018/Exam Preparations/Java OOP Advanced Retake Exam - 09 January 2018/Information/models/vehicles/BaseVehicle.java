package panzer.models.vehicles;

import panzer.contracts.Part;
import panzer.contracts.Vehicle;
import panzer.models.BaseEntity;

import java.math.BigDecimal;

public abstract class BaseVehicle extends BaseEntity implements Vehicle {

    //Model – a string.
    //Weight – a floating-point number.
    //Price – a decimal number.
    //Attack – an integer.
    //Defense – an integer.
    //HitPoints – an integer.
    private int attack;
    private int defense;
    private int hitPoints;

    protected BaseVehicle(String model, Double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        super(model, weight, price);
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    @Override
    public double getTotalWeight() {
        return 0;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }

    @Override
    public long getTotalAttack() {
        return 0;
    }

    @Override
    public long getTotalDefense() {
        return 0;
    }

    @Override
    public long getTotalHitPoints() {
        return 0;
    }

    @Override
    public void addArsenalPart(Part arsenalPart) {

    }

    @Override
    public void addShellPart(Part shellPart) {

    }

    @Override
    public void addEndurancePart(Part endurancePart) {

    }

    @Override
    public Iterable<Part> getParts() {
        return null;
    }

    @Override
    public String getModel() {
        return null;
    }
}
