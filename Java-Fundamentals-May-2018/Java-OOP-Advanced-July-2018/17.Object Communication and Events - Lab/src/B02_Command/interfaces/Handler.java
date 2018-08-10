package B02_Command.interfaces;

import B02_Command.enums.LogType;

public interface Handler {
    void handle(LogType type, String message);

    void setSuccessor(Handler handler);
}
