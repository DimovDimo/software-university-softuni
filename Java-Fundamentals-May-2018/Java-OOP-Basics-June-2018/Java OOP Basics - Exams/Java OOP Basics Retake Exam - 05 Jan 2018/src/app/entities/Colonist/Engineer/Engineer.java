package app.entities.Colonist.Engineer;

import app.entities.Colonist.Colonist;

public abstract class Engineer extends Colonist {
    public Engineer(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }

    protected int getAgeBonus(){
        if (super.getAge() >= 30){
            return 2;
        } else {
            return 0;
        }
    }

    public int getEnginetClassBonus(){
        return 3;
    }
}
