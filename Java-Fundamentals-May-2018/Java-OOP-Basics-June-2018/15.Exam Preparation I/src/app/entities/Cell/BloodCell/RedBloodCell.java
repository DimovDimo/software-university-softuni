package app.entities.Cell.BloodCell;

public class RedBloodCell extends BloodCell {
    private int velocity;

    public RedBloodCell(String id, int health, int positionRow, int positionCol, int velocity) {
        super(id, health, positionRow, positionCol);
        this.velocity = velocity;
    }

    @Override
    public int getSpecialProperty() {
        return this.velocity;
    }

    @Override
    public int getEnergy() {
        return super.getHealth() + velocity;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
