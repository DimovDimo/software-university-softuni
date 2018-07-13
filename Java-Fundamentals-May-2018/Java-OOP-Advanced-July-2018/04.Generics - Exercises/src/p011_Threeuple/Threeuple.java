package p011_Threeuple;

public class Threeuple<F, S, T> {

    private F firstItem;
    private S secondItem;
    private T thirdItem;

    public Threeuple(F firstItem, S secondItem, T thirdItem) {
        this.setFirstItem(firstItem);
        this.setSecondItem(secondItem);
        this.setThirdItem(thirdItem);
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

    public T getThirdItem() {
        return thirdItem;
    }

    private void setThirdItem(T thirdItem) {
        this.thirdItem = thirdItem;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(this.firstItem).append( " -> ").append(this.secondItem).append(" -> ").append(this.thirdItem).toString();
    }
}
