package p02_GenericBoxOfInteger;

public class Box<T> {

    private Integer parameter;

    public Box(Integer parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return String.format("%s: %s",
                this.parameter.getClass().getCanonicalName(),
                this.parameter);
    }
}
