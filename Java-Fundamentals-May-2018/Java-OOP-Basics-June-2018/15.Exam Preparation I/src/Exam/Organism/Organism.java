package Exam.Organism;

import Exam.Cluster.Cluster;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Organism {

    private String name;
    private Map<String, Cluster> clusters;

    public Organism(String name) {
        this.name = name;
        this.clusters = new LinkedHashMap<>();
    }

    public Map<String, Cluster> getClusters() {
        return clusters;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
