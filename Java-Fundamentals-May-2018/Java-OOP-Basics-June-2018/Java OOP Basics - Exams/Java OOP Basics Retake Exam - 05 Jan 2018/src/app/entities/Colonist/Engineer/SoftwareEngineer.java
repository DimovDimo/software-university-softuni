package app.entities.Colonist.Engineer;

public class SoftwareEngineer extends Engineer {
    public SoftwareEngineer(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }

    @Override
    public int getPotential() {
        int flatBonus = 2;
        return  super.getEnginetClassBonus() + super.getTalent() + super.getAgeBonus() + flatBonus;
    }

}
