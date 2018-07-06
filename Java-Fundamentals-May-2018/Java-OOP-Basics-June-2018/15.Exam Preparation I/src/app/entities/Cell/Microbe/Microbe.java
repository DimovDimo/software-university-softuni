package app.entities.Cell.Microbe;

import app.entities.Cell.Cell;

public abstract class Microbe extends Cell {
    private int virulence;

    public Microbe(String id, int health, int positionRow, int positionCol, int virulence) {
        super(id, health, positionRow, positionCol);
        this.virulence = virulence;
    }

    public int getVirulence() {
        return virulence;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
