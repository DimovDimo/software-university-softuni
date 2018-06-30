package p08_MilitaryElite.impl;

import p08_MilitaryElite.contracts.Repair;

public class RepairImpl implements Repair {
    private String partName;
    private int hourseWorked;

    public RepairImpl(String partName, int hourseWorked) {
        this.partName = partName;
        this.hourseWorked = hourseWorked;
    }

    @Override
    public String toString() {
        return String.format("  Part Name: %s Hours Worked: %d", this.partName, this.hourseWorked);
    }
}
