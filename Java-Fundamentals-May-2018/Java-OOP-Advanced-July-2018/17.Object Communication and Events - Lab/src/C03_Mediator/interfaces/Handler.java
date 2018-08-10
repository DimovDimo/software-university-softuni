package C03_Mediator.interfaces;

import C03_Mediator.enums.LogType;

public interface Handler {
    void handle(LogType type, String message);

    void setSuccessor(Handler handler);
}
