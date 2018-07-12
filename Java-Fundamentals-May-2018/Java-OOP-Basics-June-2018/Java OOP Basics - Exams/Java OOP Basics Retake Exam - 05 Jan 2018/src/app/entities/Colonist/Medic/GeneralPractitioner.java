package app.entities.Colonist.Medic;

public class GeneralPractitioner extends Medic {
    public GeneralPractitioner(String id, String familyId, int talent, int age, String sing) {
        super(id, familyId, talent, age, sing);
    }

    @Override
    public int getPotential() {
        int ageBonus = 0;
        if (super.getAge() >= 15){
            ageBonus = 1;
        }

        int signBonus = 0;
        if (super.getSign().equals("caring")){
            signBonus = 1;
        } else if (super.getSign().equals("careless")){
            signBonus = -2;
        }

        return super.getTalent() + super.getMedicClassBonus() + ageBonus + signBonus;
    }

}
