package app.core;

import app.entities.Cell.BloodCell.RedBloodCell;
import app.entities.Cell.BloodCell.WhiteBloodCell;
import app.entities.Cell.Cell;
import app.entities.Cell.Microbe.Bacteria;
import app.entities.Cell.Microbe.Fungi;
import app.entities.Cell.Microbe.Virus;
import app.entities.Cluster.Cluster;
import app.entities.Organism.Organism;

import java.util.HashMap;
import java.util.Map;

public class HealthManager {
    private Map<String, Organism> organisms;

    public HealthManager() {
        this.organisms = new HashMap<>();
    }

    public  String checkCondition(String organismName){
        if (this.organisms.containsKey(organismName)){
            Organism organism = this.organisms.get(organismName);
            return organism.toString();
        }

        return null;
    }

    public  String createOrganism(String name){
        if (this.organisms.containsKey(name)){
            return String.format("Organism %s already exists", name);
        }

        Organism organism = new Organism(name);
        this.organisms.put(name, organism);
        return String.format("Created organism %s", name);
    }

    public  String addCluster(String organismName, String id, int rows, int cols){
        if (this.organisms.containsKey(organismName)){
            if (this.organisms.get(organismName).containsId(id) == false){
                Cluster cluster = new Cluster(id, rows, cols);
                this.organisms.get(organismName).getClusters().add(cluster);
                return String.format("Organism %s: Created cluster %s", organismName, id);
            }
        }

        return null;
    }

    public  String addCell(String organismName, String clusterId, String cellType, String cellId, int health, int positionRow, int positionCol, int additionalProperty){
        if (this.organisms.containsKey(organismName)){
            if (this.organisms.get(organismName).containsId(clusterId)){
                if (this.organisms.get(organismName).getClusterById(clusterId).inCluster(positionRow, positionCol)) {
                    Cell cell = null;
                    switch (cellType) {
                        case "WhiteBloodCell":
                            cell = new WhiteBloodCell(cellId, health, positionRow, positionCol, additionalProperty);
                            break;
                        case "RedBloodCell":
                            cell = new RedBloodCell(cellId, health, positionRow, positionCol, additionalProperty);
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

                    if (cell != null) {
                        this.organisms.get(organismName).getClusterById(clusterId).addCell(cell);
                    }

                    return String.format("Organism %s: Created cell %s in cluster %s", organismName, cellId, clusterId);
                }
            }
        }

        return null;
    }

    public  String activateCluster(String organismName){
        if (this.organisms.containsKey(organismName)){
            if (this.organisms.get(organismName).getClusters().size() != 0) {
                String clusterId = this.organisms.get(organismName).activateCluster();
                return String.format("Organism %s: Activated cluster %s. Cells left: %d", organismName, clusterId, 1);
            }
        }

        return null;
    }


}
