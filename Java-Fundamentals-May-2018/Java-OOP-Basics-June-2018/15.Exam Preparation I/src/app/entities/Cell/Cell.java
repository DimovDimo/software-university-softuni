package app.entities.Cell;

import app.entities.Cell.BloodCell.WhiteBloodCell;

public abstract class Cell {
    private String id;
    private int health;
    private int positionRow;
    private int positionCol;

    public Cell(String id, int health, int positionRow, int positionCol) {
        this.id = id;
        this.health = health;
        this.positionRow = positionRow;
        this.positionCol = positionCol;
    }

    public int getPositionRow() {
        return positionRow;
    }

    public int getPositionCol() {
        return positionCol;
    }

    public int getHealth() {
        return health;
    }

    public abstract int getSpecialProperty();

    public abstract int getEnergy();

    @Override
    public String toString() {
        StringBuilder cell = new StringBuilder(System.lineSeparator())
                .append(String.format("------Cell %s [%d,%d]", this.id, this.positionRow, this.positionCol));

        String cellType = this.getClass().getSimpleName();
        switch (cellType){
            case "WhiteBloodCell":
                cell.append(System.lineSeparator())
                        .append(String.format("--------Health: %d | Size: %d | Energy: %d", this.health, this.getSpecialProperty(), this.getEnergy()));
                break;
            case "RedBloodCell":
                cell.append(System.lineSeparator())
                        .append(String.format("--------Health: %d | Velocity: %d | Energy: %d", this.health, this.getSpecialProperty(), this.getEnergy()));
                break;
            case "Bacteria":
            case "Fungi":
            case "Virus":
                cell.append(System.lineSeparator())
                        .append(String.format("--------Health: %d | Virulence: %d | Energy: %d", this.health, this.getSpecialProperty(), this.getEnergy()));
                break;
        }

        return cell.toString();
    }
}
