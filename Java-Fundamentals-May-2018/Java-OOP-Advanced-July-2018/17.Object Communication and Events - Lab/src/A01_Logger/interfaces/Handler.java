package A01_Logger.interfaces;

import A01_Logger.enums.LogType;

public interface Handler {
    void handle(LogType type, String message);

    void setSuccessor(Handler handler);
}
