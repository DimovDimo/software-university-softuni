package B02_Command.inplementations.loggers;

import B02_Command.abstracts.Logger;
import B02_Command.enums.LogType;

public class EventLogger extends Logger {

    public EventLogger() {
    }

    @Override
    public void handle(LogType type, String message) {
        if (type == LogType.EVENT){
            System.out.println(type.name() + ": " + message);
            return;
        }

        super.passToSuccessor(type, message);
    }
}
