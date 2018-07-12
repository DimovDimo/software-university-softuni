package app.entities.Colonist.Medic;

import app.entities.Colonist.Colonist;

public abstract class Medic extends Colonist {
    private String sign;

    public Medic(String id, String familyId, int talent, int age, String sign) {
        super(id, familyId, talent, age);
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public int getMedicClassBonus(){
        return 2;
    }
}
