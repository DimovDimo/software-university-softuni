package B02_Command.abstracts;

import B02_Command.enums.LogType;
import B02_Command.interfaces.Handler;

public abstract class Logger implements Handler {

    private Handler successor;

    public Logger(Handler successor) {
        this.successor = successor;
    }

    protected Logger() {
    }

    public void passToSuccessor(LogType type, String message){
        if (this.successor != null){
            this.successor.handle(type, message);
        }
    }

    @Override
    public void setSuccessor(Handler handler) {
        this.successor = handler;
    }
}
