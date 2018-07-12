package app.entities.Colony;

import app.entities.Colonist.Colonist;
import app.entities.Family.Family;

import java.util.*;
import java.util.stream.Collectors;

public class Colony {
    private int maxFamilyCount;
    private int maxFamilyCapacity;
    private Map<String, Family> families;

    public Colony(int maxFamilyCount, int maxFamilyCapacity) {
        this.maxFamilyCount = maxFamilyCount;
        this.maxFamilyCapacity = maxFamilyCapacity;
        this.families = new HashMap<>();
    }

    public int getMaxFamilyCount() {
        return maxFamilyCount;
    }

    public int getMaxFamilyCapacity() {
        return maxFamilyCapacity;
    }

    public Map<String, Family> getFamilies() {
        return families;
    }

    public void addColonist(Colonist colonist){
        String familyId = colonist.getFamilyId();
        if(this.families.containsKey(familyId)){
            if (this.families.get(familyId).getColonists().size() >= this.maxFamilyCapacity){
                throw new IndexOutOfBoundsException("family is full");
            }

        } else if (this.families.size() >= this.maxFamilyCount){
            throw new IndexOutOfBoundsException("colony is full");
        }

        if (this.families.containsKey(familyId)){
            this.families.get(familyId).getColonists().add(colonist);
        } else {
            Family family = new Family(familyId);
            family.getColonists().add(colonist);
            this.families.put(familyId, family);
        }
    }

    public void removeColonist(String familyId, String memberId){
        if (this.families.containsKey(familyId)){
            this.families.get(familyId).removeColonistById(memberId);
            if (this.families.get(familyId).getColonists().size() >= 0){
                this.removeFamily(familyId);
            }
        }
    }

    public void removeFamily(String id){
        this.families.remove(id);
    }

    public List<Colonist> getColonistsByFamilyId(String familyId){
        return (List<Colonist>) this.families.get(familyId).getColonists();
    }

    public void grow(int years){
        for (Family family : families.values()) {
            for (Colonist colonist : family.getColonists()) {
                colonist.grow(years);
            }
        }
    }

    public int getPotential(){
        int totalPotencial = 0;
        for (Family family : families.values()) {
            for (Colonist colonist : family.getColonists()) {
                totalPotencial += colonist.getPotential();
            }
        }

        return totalPotencial;
    }

    public String getCapacity(){
        StringBuilder capacity = new StringBuilder(String.format("families: %d/%d", this.families.size(), this.maxFamilyCount));

        for (Family family : families.values().stream()
                .sorted((f1, f2) -> f1.getId().compareTo(f2.getId())).collect(Collectors.toList())) {
            capacity.append(System.lineSeparator())
                    .append(family.toStringWithMaxFamilyCapacity(this.maxFamilyCapacity));
        }

        return capacity.toString();
    }

    public String family(String familyId){
        if (this.families.containsKey(familyId) == false){
            return "family does not exist";
        }

        Family currentFamily = this.families.get(familyId);
        StringBuilder family = new StringBuilder(String.format("%s:", currentFamily.getId()));

        for (Colonist colonist : currentFamily.getColonists().stream()
                .sorted((c1, c2) -> c1.getId().compareTo(c2.getId())).collect(Collectors.toList())) {
            family.append(System.lineSeparator())
                    .append(String.format("-%s: %d", colonist.getId(), colonist.getPotential()));
        }

        return family.toString();
    }
}
