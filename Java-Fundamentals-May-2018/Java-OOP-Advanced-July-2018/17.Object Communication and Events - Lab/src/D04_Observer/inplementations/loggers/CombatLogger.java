package D04_Observer.inplementations.loggers;

import D04_Observer.abstracts.Logger;
import D04_Observer.enums.LogType;

public class CombatLogger extends Logger {

    public CombatLogger() {

    }

    @Override
    public void handle(LogType type, String message) {
        if (type == LogType.ATTACK || type == LogType.MAGIC){
            System.out.println(type.name() + ": " + message);
            return;
        }

        super.passToSuccessor(type, message);
    }
}
