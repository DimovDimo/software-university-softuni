package app.entities.Cell.BloodCell;

public class WhiteBloodCell extends BloodCell {
    private int size;

    public WhiteBloodCell(String id, int health, int positionRow, int positionCol, int size) {
        super(id, health, positionRow, positionCol);
        this.size = size;
    }

    @Override
    public int getSpecialProperty() {
        return this.size;
    }

    @Override
    public int getEnergy() {
        return (super.getHealth() + size) * 2;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
