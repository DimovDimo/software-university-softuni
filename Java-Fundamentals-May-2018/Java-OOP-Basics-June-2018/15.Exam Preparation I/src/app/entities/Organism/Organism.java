package app.entities.Organism;

import app.entities.Cluster.Cluster;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Organism {
    private String name;
    private Collection<Cluster> clusters;

    public Organism(String name) {
        this.name = name;
        this.clusters = new LinkedList<>();
    }

    public String activateCluster(){
        for (Cluster cluster : this.clusters) {

//            while (true){
//                if (cluster.getCells().size() <= 1){
//                    break;
//                }
//
//
//                for (int row = 0; row < cluster.getRows(); row++) {
//                    for (int col = 0; col < cluster.getCols(); col++) {
//
//                    }
//                }
//            }
            return cluster.getId();
        }

        return null;
    }

    public Cluster getClusterById(String id){
        for (Cluster cluster : this.clusters) {
            if (cluster.getId().equals(id)){
                return cluster;
            }
        }

        return null;
    }

    public Collection<Cluster> getClusters() {
        return clusters;
    }

    public boolean containsId(String id) {
        for (Cluster cluster : this.clusters) {
            if (cluster.getId().equals(id)){
                return true;
            }
        }

        return false;
    }

    private int getCellCount(){
        int cellCount = 0;
        for (Cluster cluster : this.clusters) {
            cellCount += cluster.getCellCount();
        }

        return cellCount;
    }

    @Override
    public String toString() {
        StringBuilder organismOutput = new StringBuilder();
        organismOutput.append(String.format("Organism - %s", this.name))
                .append(System.lineSeparator())
                .append(String.format("--Clusters: %d", this.clusters.size()))
                .append(System.lineSeparator())
                .append(String.format("--Cells: %d", this.getCellCount()));

        for (Object cluster : this.clusters.stream().sorted((c1, c2) -> {
            return c2.getCellCount() - c1.getCellCount();
        }).collect(Collectors.toList())) {
            organismOutput
                    .append(System.lineSeparator())
                    .append(cluster.toString());
        }

        return organismOutput.toString();
    }
}
