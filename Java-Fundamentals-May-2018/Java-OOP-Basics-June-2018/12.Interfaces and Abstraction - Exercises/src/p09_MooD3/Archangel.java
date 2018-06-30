package p09_MooD3;

import java.util.Arrays;

public class Archangel extends Characters {

    public Archangel(String username, String type, String specialPoint, String level) {
        super(username, type, specialPoint, level);
        super.setMultiplyLevelAndPoints(
                this.setMultiplyLevelAndPointsArchangel());
    }

    public String setMultiplyLevelAndPointsArchangel() {
        int mana = Integer.parseInt(super.getSpecialPoint());
        int level = Integer.parseInt(super.getLevel());
        return String.format("%d", mana * level);
    }

    @Override
    public void hashPassword() {
        super.setHashedPassword(
                new StringBuilder(super.getUsername())
                        .reverse()
                        .append(super.getUsername().length() * 21)
                        .toString());
    }
}
