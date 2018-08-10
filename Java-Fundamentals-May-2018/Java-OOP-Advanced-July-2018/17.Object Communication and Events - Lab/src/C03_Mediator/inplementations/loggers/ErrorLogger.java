package C03_Mediator.inplementations.loggers;

import C03_Mediator.abstracts.Logger;
import C03_Mediator.enums.LogType;

public class ErrorLogger extends Logger {

    public ErrorLogger() {
    }

    @Override
    public void handle(LogType type, String message) {
        if (type == LogType.ERROR){
            System.out.println(type.name() + ": " + message);
            return;
        }

        super.passToSuccessor(type, message);
    }
}
