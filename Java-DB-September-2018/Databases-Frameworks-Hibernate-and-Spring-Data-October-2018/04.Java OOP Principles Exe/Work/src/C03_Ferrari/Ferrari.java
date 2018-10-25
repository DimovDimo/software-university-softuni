package C03_Ferrari;

public class Ferrari implements Car {

    private String model;
    private String driver;

    public Ferrari(String model, String driver) {
        this.model = model;
        this.driver = driver;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public void brakes() {
        System.out.print("Brakes!");
    }

    @Override
    public void gasPedal() {
        System.out.print("Zadu6avam sA!");
    }
}
