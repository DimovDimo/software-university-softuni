package p09_MooD3;

public abstract class Characters implements Passwordable {
    private String username;
    private String type;
    private String specialPoint;
    private String level;
    private String hashedPassword;
    private String multiplyLevelAndPoints;

    protected Characters(String username, String type, String specialPoint, String level) {
        this.username = username;
        this.type = type;
        this.specialPoint = specialPoint;
        this.level = level;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setMultiplyLevelAndPoints(String multiplyLevelAndPoints) {
        this.multiplyLevelAndPoints = multiplyLevelAndPoints;
    }

    public String getSpecialPoint() {
        return specialPoint;
    }

    public String getLevel() {
        return level;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" | \"%s\" -> %s%n%s",
                this.username,
                this.hashedPassword,
                this.type,
                this.multiplyLevelAndPoints);
    }
}
