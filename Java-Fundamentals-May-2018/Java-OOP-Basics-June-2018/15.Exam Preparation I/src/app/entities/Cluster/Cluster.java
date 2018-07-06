package app.entities.Cluster;

import app.entities.Cell.Cell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Cluster {
    private String id;
    private int rows;
    private int cols;
    private Collection<Cell> cells;

    public Cluster(String id, int rows, int cols) {
        this.id = id;
        this.rows = rows;
        this.cols = cols;
        this.cells = new ArrayList<>();
    }

    public Cluster(String id, int rows, int cols, Collection<Cell> cells) {
        this.id = id;
        this.rows = rows;
        this.cols = cols;
        this.cells = cells;
    }

    public Collection<Cell> getCells() {
        return cells;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean inCluster(int row, int col){
        if (row < 0 || this.rows < row || col < 0 || this.cols < col){
            return false;
        }

        return true;
    }

    public void addCell(Cell cell){
        this.cells.add(cell);
    }

    public int getCellCount(){
        return this.cells.size();
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder cluster = new StringBuilder(String.format("----Cluster %s", this.id));


        for (Cell cell : this.cells.stream()
                .sorted(Comparator.comparing(Cell::getPositionRow)
                        .thenComparing(Cell::getPositionCol)).collect(Collectors.toList())) {
            cluster.append(cell.toString());
        }

        return cluster.toString();
    }
}
