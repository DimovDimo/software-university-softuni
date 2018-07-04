package app.entities.cars;

public class ShowCar extends BaseCar {

    private int stars;

    public ShowCar(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        this.stars = 0;
    }

    @Override
    public String toString() {
        StringBuilder showCar = new StringBuilder(super.toString())
                .append(System.lineSeparator())
                .append(String.format("%d *", this.stars));

        return showCar.toString();
    }
}
