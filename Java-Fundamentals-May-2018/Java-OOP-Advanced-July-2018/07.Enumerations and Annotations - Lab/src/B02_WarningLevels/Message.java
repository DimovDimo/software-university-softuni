package B02_WarningLevels;

import java.util.Comparator;

public class Message {

    private Importance importance;
    private String massage;

    public Message(String importance, String massage) {
        this.importance = Enum.valueOf(Importance.class, importance.toUpperCase());
        this.massage = massage;
    }

    @Override
    public String toString() {
        return String.format("%s: %s",
                this.importance.name(),
                this.massage);
    }
}
