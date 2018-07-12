package p01_GenericBox;

public class Box<T> {

    private String parameter;

    public Box(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return String.format("%s: %s",
                this.parameter.getClass().getCanonicalName(),
                this.parameter);
    }
}
