package p03_GenericScale;

import javafx.util.Pair;

public class Scale<T extends Comparable<T>> {

    private T left;
    private T right;

    public Scale(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getHeavier(){
        if (this.left.equals(this.right)){
            return null;
        } else if (this.left.compareTo(this.right) > 0){
            return this.left;
        } else {
            return this.right;
        }
    }
}
