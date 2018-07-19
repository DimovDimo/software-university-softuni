package I09_TrafficLights;

public enum TrafficLights {
    RED,
    GREEN,
    YELLOW;

    @Override
    public String toString() {
        return this.name();
    }
}
