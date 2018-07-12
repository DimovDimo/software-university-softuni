package app.entities.Colonist.Medic;

public class Surgeon extends Medic {
    public Surgeon(String id, String familyId, int talent, int age, String sing) {
        super(id, familyId, talent, age, sing);
    }

    @Override
    public int getPotential() {
        int ageBonus = 0;
        if (25 < super.getAge() && super.getAge() < 35){
            ageBonus = 2;
        }

        int signBonus = 0;
        if (super.getSign().equals("precise")){
            signBonus = 3;
        } else if (super.getSign().equals("butcher")){
            signBonus = -3;
        }

        return super.getTalent() + super.getMedicClassBonus() + ageBonus + signBonus;
    }

}
