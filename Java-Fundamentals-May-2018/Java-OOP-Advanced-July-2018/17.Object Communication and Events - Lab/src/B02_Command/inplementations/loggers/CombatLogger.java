package B02_Command.inplementations.loggers;

import B02_Command.abstracts.Logger;
import B02_Command.enums.LogType;

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
