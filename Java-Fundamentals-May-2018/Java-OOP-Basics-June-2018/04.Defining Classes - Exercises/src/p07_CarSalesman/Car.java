package p07_CarSalesman;

public class Car {
    private static final String DEFAULT_NA = "n/a";
    private String model;
    private String engine;
    private String weight;
    private String color;

    public Car(String model, String engine) {
        this.model = model;
        this.engine = engine;
        this.weight = DEFAULT_NA;
        this.color = DEFAULT_NA;
    }

    public String getEngine() {
        return engine;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toStringModelAndEngine() {
        return String.format("%s:%n%s:%n", this.model, this.engine);
    }

    public String toStringWeightAndColor() {
        return String.format("Weight: %s%nColor: %s%n", this.weight, this.color);
    }
}
