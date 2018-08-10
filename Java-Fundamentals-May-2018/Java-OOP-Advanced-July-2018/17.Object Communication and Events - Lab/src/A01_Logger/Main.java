package A01_Logger;

import A01_Logger.abstracts.Logger;
import A01_Logger.enums.LogType;
import A01_Logger.inplementations.CombatLogger;
import A01_Logger.inplementations.ErrorLogger;
import A01_Logger.inplementations.EventLogger;

public class Main {
    public static void main(String[] args) {
        Logger combatLog = new CombatLogger();
        Logger eventLog = new EventLogger();
        Logger errorLog = new ErrorLogger();

        combatLog.setSuccessor(errorLog);

        combatLog.handle(LogType.ATTACK, "some attack");
        combatLog.handle(LogType.ERROR, "some error");
        combatLog.handle(LogType.EVENT, "some event");
    }
}
