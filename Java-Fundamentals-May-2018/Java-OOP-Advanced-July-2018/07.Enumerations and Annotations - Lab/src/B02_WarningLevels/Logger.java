package B02_WarningLevels;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private List<Message> messages;
    private Importance status;

    public Logger(String status) {
        this.messages = new ArrayList<>();
        this.status = Enum.valueOf(Importance.class, status.toUpperCase());
    }

    public void addMessage(String status, String message){
        Importance currentStatus = Enum.valueOf(Importance.class, status.toUpperCase());
        if (currentStatus.ordinal() >= this.status.ordinal()){
            this.messages.add(new Message(status, message));
        }
    }

    public Iterable<Message> getMessages(){
        return this.messages;
    }
}
