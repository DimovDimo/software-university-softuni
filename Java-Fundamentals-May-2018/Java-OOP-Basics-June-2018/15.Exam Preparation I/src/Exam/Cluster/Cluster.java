package Exam.Cluster;

import Exam.Cell.Cell;

import java.util.LinkedList;
import java.util.List;

public class Cluster {

    private String id;
    private int rows;
    private int cols;
    private List<Cell> cells;

    public Cluster(String id, int rows, int cols) {
        this.id = id;
        this.rows = rows;
        this.cols = cols;
        this.cells = new LinkedList<>();
    }

    public List<Cell> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
