package panzer.models.vehicles;

import java.math.BigDecimal;

public class Vanguard extends BaseVehicle {

    //A tank-like land vehicle. The Vanguard modifies its properties in the following way:
    //Weight – increases its given weight by 100%.
    //Attack – decreases its given attack by 25%.
    //Defense – increases its given defense by 50%.
    //HitPoints – increases its given hitPoints by 75%.
    private static final Double WEIGHT = 2.0;
    private static final Double ATTACK = 0.75;
    private static final Double DEFENSE = 1.5;
    private static final Double HITPOINTS = 1.75;

    public Vanguard(String model, Double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        super(model, weight, price, attack, defense, hitPoints);
    }
}
