package app.entities.Colonist.Soldier;

import app.entities.Colonist.Colonist;

public class Soldier extends Colonist {
    public Soldier(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }

    @Override
    public int getPotential() {
        int classBonus = 3;
        int ageBonus = 3;
        return super.getTalent() + classBonus + ageBonus;
    }

}
