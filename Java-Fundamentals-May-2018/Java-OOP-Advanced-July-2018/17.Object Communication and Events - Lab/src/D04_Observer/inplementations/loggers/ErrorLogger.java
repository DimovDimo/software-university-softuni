package D04_Observer.inplementations.loggers;

import D04_Observer.abstracts.Logger;
import D04_Observer.enums.LogType;

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
