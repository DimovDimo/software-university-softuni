package p07_CarSalesman;

public class Engine {
    private static final String DEFAULT_NA = "n/a";
    private String model;
    private String power;
    private String displacement;
    private String efficiency;

    public Engine(String model, String power) {
        this.model = model;
        this.power = power;
        this.displacement = DEFAULT_NA;
        this.efficiency = DEFAULT_NA;
    }

    public String getModel() {
        return model;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    public String toString() {
        return String.format("Power: %s%nDisplacement: %s%nEfficiency: %s%n", this.power, this.displacement, this.efficiency);
    }
}
