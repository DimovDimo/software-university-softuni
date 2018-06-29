package p03_Ferrari;

public class Ferrari implements Car {
    private String nameOfDriver;

    public Ferrari(String nameOfDriver) {
        this.setNameOfDriver(nameOfDriver);
    }

    public void setNameOfDriver(String nameOfDriver) {
        this.nameOfDriver = nameOfDriver;
    }

    @Override
    public String useBrakes() {
        return "Brakes!";
    }

    @Override
    public String pushTheGasPedal() {
        return "Zadu6avam sA!";
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s/%s", MODEL, this.useBrakes(), this.pushTheGasPedal(), this.nameOfDriver);
    }
}
