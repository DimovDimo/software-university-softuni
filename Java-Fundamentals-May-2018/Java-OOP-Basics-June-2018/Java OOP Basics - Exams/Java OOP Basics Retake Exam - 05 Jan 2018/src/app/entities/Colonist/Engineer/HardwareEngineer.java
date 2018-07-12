package app.entities.Colonist.Engineer;

public class HardwareEngineer extends Engineer {
    public HardwareEngineer(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }

    @Override
    public int getPotential() {
        int bonus = 0;
        if (super.getAge() < 18){
            bonus = 2;
        }

        return super.getEnginetClassBonus() + super.getTalent() + super.getAgeBonus() + bonus;
    }

}
