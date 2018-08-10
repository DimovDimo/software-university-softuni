package A01_Logger.inplementations;

import A01_Logger.abstracts.Logger;
import A01_Logger.enums.LogType;
import A01_Logger.interfaces.Handler;

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
