package Exam;

import Exam.Cell.BloodCell.RedBloodCell;
import Exam.Cell.BloodCell.WhiteBloodCell;
import Exam.Cell.Cell;
import Exam.Cell.Microbe.Bacteria;
import Exam.Cell.Microbe.Fungi;
import Exam.Cell.Microbe.Virus;
import Exam.Cluster.Cluster;
import Exam.Organism.Organism;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HealthManager {
    private Map<String, Organism> organisms;

    public HealthManager() {
        this.organisms = new HashMap<>();
    }

    public String checkCondition(String organismName){
        return this.organisms.get(organismName).toString();
    }

    public String createOrganism(String name) {
        if (this.organisms.containsKey(name)){
            Organism organism = new Organism(name);
            this.organisms.put(name, organism);

            return String.format("Created organism %s", name);

        } else {
            return String.format("Organism %s already exists", name);
        }
    }

    public	String addCluster(String organismName, String id, int rows, int cols) {
        Cluster cluster = new Cluster(id, rows, cols);
        if (this.organisms.containsKey(organismName)){
            if (this.organisms.get(organismName).getClusters().containsKey(id) == false){
                this.organisms.get(organismName).getClusters().put(id, cluster);
            }
        }

        return String.format("Organism %s: Created cluster %s", organismName, id);
    }

    public	String addCell(String organismName, String clusterId, String cellType, String cellId, int health, int positionRow, int positionCol, int additionalProperty) {

        Cell cell = null;
        switch (cellType){
            case "RedBloodCell":
                cell = new RedBloodCell(cellId, health, positionRow, positionCol, additionalProperty);
                break;
            case "WhiteBloodCell":
                cell = new WhiteBloodCell(cellId, health, positionRow, positionCol, additionalProperty);
                break;
            case "Bacteria":
                cell = new Bacteria(cellId, health, positionRow, positionCol, additionalProperty);
                break;
            case "Fungi":
                cell = new Fungi(cellId, health, positionRow, positionCol, additionalProperty);
                break;
            case "Virus":
                cell = new Virus(cellId, health, positionRow, positionCol, additionalProperty);
                break;
        }

        this.organisms.get(organisms).getClusters().get(clusterId).getCells().add(cell);

        return String.format("Organism %s: Created cell %s in cluster %s", organismName, cellId, clusterId);
    }

    public	String activateCluster(String organismName)  {
        return null;
    }

}
