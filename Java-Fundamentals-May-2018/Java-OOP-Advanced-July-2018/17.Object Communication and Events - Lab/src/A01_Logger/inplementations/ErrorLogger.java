package A01_Logger.inplementations;

import A01_Logger.abstracts.Logger;
import A01_Logger.enums.LogType;

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
