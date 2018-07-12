package p10_Tuple;

public class Tuple<F, S> {

    private F firstItem;
    private S secondItem;

    public Tuple(F firstItem, S secondItem) {
        this.setFirstItem(firstItem);
        this.setSecondItem(secondItem);
    }

    public F getFirstItem() {
        return firstItem;
    }

    private void setFirstItem(F firstItem) {
        this.firstItem = firstItem;
    }

    public S getSecondItem() {
        return secondItem;
    }

    private void setSecondItem(S secondItem) {
        this.secondItem = secondItem;
    }

    @Override
    public String toString() {
        return this.firstItem + " -> " + this.secondItem;
    }
}
