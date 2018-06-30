package p09_MooD3;

public class Demon extends Characters {

    public Demon(String username, String type, String specialPoint, String level) {
        super(username, type, specialPoint, level);
        super.setMultiplyLevelAndPoints(
                this.setMultiplyLevelAndPointsDemon());
    }

    public String setMultiplyLevelAndPointsDemon() {
        double energy = Double.parseDouble(super.getSpecialPoint());
        int level = Integer.parseInt(super.getLevel());
        return String.format("%.1f", energy * level);
    }

    @Override
    public void hashPassword() {
        super.setHashedPassword(
                super.getUsername().length() * 217 + "");
    }
}
