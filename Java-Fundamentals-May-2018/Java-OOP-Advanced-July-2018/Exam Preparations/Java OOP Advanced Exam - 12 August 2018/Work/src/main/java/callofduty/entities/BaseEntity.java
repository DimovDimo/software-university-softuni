package callofduty.entities;

import callofduty.interfaces.Identifiable;
import callofduty.interfaces.Rateable;

public abstract class BaseEntity implements Identifiable, Rateable {

    private String id;
    private Double rating;

    protected BaseEntity(String id, Double rating) {
        this.id = id;
        this.rating = rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }
}
