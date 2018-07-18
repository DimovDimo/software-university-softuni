package C03_CoffeeMachine;

import C03_CoffeeMachine.Enumerations.CoffeeSize;
import C03_CoffeeMachine.Enumerations.CoffeeType;

public class Coffee {
    private CoffeeSize coffeeSize;
    private CoffeeType coffeeType;

    public Coffee(CoffeeSize coffeeSize, CoffeeType coffeeType) {
        this.coffeeSize = coffeeSize;
        this.coffeeType = coffeeType;
    }

    @Override
    public String toString() {
        return String.format("%s %s",
                coffeeSize.toString(),
                coffeeType.toString());
    }
}
