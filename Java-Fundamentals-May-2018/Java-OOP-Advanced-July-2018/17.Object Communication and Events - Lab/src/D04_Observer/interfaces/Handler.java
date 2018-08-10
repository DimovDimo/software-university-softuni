package D04_Observer.interfaces;

import D04_Observer.enums.LogType;

public interface Handler {
    void handle(LogType type, String message);

    void setSuccessor(Handler handler);
}
