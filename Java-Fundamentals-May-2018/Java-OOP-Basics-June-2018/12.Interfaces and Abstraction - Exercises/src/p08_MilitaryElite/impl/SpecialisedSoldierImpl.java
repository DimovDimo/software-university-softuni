package p08_MilitaryElite.impl;

import p08_MilitaryElite.contracts.SpecialisedSoldier;

import java.util.ArrayList;
import java.util.List;

public abstract class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {

    private static final String AIR_FORCES_CORPSTYPE = "Airforces";
        private static final String MARINES_CORPSTYPE = "Marines";

    private String corps;

    public SpecialisedSoldierImpl(String id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary);
        this.setCorps(corps);
    }

    private void setCorps(String corps) {
        if (!AIR_FORCES_CORPSTYPE.equals(corps) && !MARINES_CORPSTYPE.equals(corps)){
            throw new IllegalArgumentException();
        }

        this.corps = corps;
    }

    public String getCorps() {
        return corps;
    }

    @Override
    public String toString() {
        StringBuilder specialisedSoldier = new StringBuilder(super.toString()).append(System.lineSeparator());

        specialisedSoldier.append(String.format("Corps: %s", this.corps));

        return specialisedSoldier.toString();
    }
}
