package panzer.models.vehicles;

import java.math.BigDecimal;

public class Revenger extends BaseVehicle {

    //A jet-like aerial vehicle. The Revenger modifies its properties in the following way:
    //Price – increases its given price by 50%.
    //Attack – increases its given attack by 150%.
    //Defense – decreases its given defense by 50%.
    //HitPoints – decreases its given hitPoints by 50%.
    private static final Double PRICE = 1.5;
    private static final Double ATTACK = 2.5;
    private static final Double DEFENSE = 0.5;
    private static final Double HITPOINTS = 0.5;

    public Revenger(String model, Double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        super(model, weight, price, attack, defense, hitPoints);
    }
}
