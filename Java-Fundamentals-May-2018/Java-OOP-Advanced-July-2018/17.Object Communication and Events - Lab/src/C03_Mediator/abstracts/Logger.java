package C03_Mediator.abstracts;

import C03_Mediator.enums.LogType;
import C03_Mediator.interfaces.Handler;

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
