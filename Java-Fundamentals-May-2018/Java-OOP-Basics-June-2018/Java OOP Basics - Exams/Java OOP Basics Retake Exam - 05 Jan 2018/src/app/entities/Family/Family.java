package app.entities.Family;

import app.entities.Colonist.Colonist;

import java.util.*;
import java.util.stream.Collectors;


public class Family {
    private String id;
    private Collection<Colonist> colonists;

    public Family(String id) {
        this.id = id;
        this.colonists = new ArrayList<>();
    }

    public String getId() {
        return id;
    }


    public Collection<Colonist> getColonists() {
        return this.colonists;
    }

    public void removeColonistById(String memberId){
        this.colonists.removeIf(colonist -> colonist.getId().equals(memberId));
    }

    public String toStringWithMaxFamilyCapacity(int maxFamilyCapacity) {
        StringBuilder family = new StringBuilder(String
                .format("-%s: %d/%d", this.id, this.colonists.size(), maxFamilyCapacity));

        return family.toString();
    }
}
