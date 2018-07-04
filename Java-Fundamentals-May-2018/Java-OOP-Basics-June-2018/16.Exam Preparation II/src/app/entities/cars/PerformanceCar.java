package app.entities.cars;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class PerformanceCar extends BaseCar {

    private List<String> addOns;

    public PerformanceCar(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        this.addOns = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder preformanceCar = new StringBuilder(super.toString())
                .append(System.lineSeparator());

        if (addOns.isEmpty()){
            preformanceCar.append("Add-ons: None");
        } else {
            preformanceCar.append(String.format("Add-ons: %s", String.join(", ", this.addOns)));
        }

        return preformanceCar.toString();
    }
}
