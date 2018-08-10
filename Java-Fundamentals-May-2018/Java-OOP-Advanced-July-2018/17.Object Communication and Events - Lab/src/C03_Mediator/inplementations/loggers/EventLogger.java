package C03_Mediator.inplementations.loggers;

import C03_Mediator.abstracts.Logger;
import C03_Mediator.enums.LogType;

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
