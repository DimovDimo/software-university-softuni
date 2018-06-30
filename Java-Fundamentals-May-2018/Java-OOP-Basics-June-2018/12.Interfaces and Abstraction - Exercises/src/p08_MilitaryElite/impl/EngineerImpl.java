package p08_MilitaryElite.impl;

import p08_MilitaryElite.contracts.Engineer;
import p08_MilitaryElite.contracts.Private;
import p08_MilitaryElite.contracts.Repair;

import java.util.Set;
import java.util.stream.Collectors;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {

    private Set<Repair> repairs;

    public EngineerImpl(String id, String firstName, String lastName, double salary, String corps, Set<Repair> repairs) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = repairs;
    }

    @Override
    public String toString() {
        StringBuilder enginer = new StringBuilder(super.toString()).append(System.lineSeparator());

        enginer.append("Repairs:");

        for (Repair repair : this.repairs) {
            enginer.append(System.lineSeparator())
                    .append(repair);
        }

        return enginer.toString();
    }
}
