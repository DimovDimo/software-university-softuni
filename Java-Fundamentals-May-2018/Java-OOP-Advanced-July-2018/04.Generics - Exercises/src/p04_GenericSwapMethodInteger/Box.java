package p04_GenericSwapMethodInteger;

public class Box<T> {

    private T parameter;

    public Box(T parameter) {
        this.parameter = parameter;
    }

    public T getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        return String.format("%s: %s",
                this.parameter.getClass().getCanonicalName(),
                this.parameter);
    }
}
