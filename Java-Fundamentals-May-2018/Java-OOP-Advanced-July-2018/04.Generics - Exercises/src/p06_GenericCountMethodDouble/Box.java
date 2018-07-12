package p06_GenericCountMethodDouble;

import java.util.List;

public class Box<T extends Comparable<T>> implements Comparable<Box<T>> {

    private T parameter;

    public Box() {
    }

    public void setParameter(T parameter) {
        this.parameter = parameter;
    }

    public static <T extends Comparable<T>> int getCountGreaterElements(List<T> list, T element) {
        int countGreaterElements = 0;
        for (T curentElement : list) {
            if (element.compareTo(curentElement) < 0) {
                countGreaterElements++;
            }
        }

        return countGreaterElements;
    }


    @Override
    public int compareTo(Box<T> other) {
        return this.parameter.compareTo(other.parameter);
    }

    @Override
    public String toString() {
        return String.format("%s: %s",
                this.parameter.getClass().getCanonicalName(),
                this.parameter);
    }
}
