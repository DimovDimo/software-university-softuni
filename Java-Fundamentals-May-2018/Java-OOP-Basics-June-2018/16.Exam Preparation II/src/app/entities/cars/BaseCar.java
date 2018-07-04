package app.entities.cars;

import app.contracts.Car;

public abstract class BaseCar implements Car {

    private String brand;
    private String model;
    private int yearOfProduction;
    private int horsepower;
    private int acceleration;
    private int suspension;
    private int durability;

    public BaseCar(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        this.brand = brand;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.horsepower = horsepower;
        this.acceleration = acceleration;
        this.suspension = suspension;
        this.durability = durability;
    }

    @Override
    public String toString() {
        StringBuilder car = new StringBuilder();
//•	The “check” command should RETURN a String representation of the CAR with the GIVEN ID:
//        o	“{brand} {model} {yearOfProduction}
//        o	 {horsepower} HP, 100 m/h in {acceleration} s
//        o	 {suspension} Suspension force, {durability} Durability”
//        o	If the car is a PerformanceCar, you must print “Add-ons: {add-ons}”, on the last line – each add-on separated by a comma and a space “, “. In case there are NO add-ons, print “None”.
//        o	If the car is a ShowCar, you must print “{stars} *”, on the last line.

        car.append(String.format("%s %s %d", this.brand, this.model, this.yearOfProduction))
                .append(System.lineSeparator())
                .append(String.format("%d HP, 100 m/h in %d s", this.horsepower, this.acceleration))
                .append(System.lineSeparator())
                .append(String.format("%d Suspension force, %d Durability", this.suspension, this.durability));
        return car.toString();
    }
}
