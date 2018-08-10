package D04_Observer.inplementations.commands;

import D04_Observer.interfaces.Command;
import D04_Observer.interfaces.Executor;

public class CommandExecutor implements Executor {
    @Override
    public void executeCommand(Command command) {
        command.execute();
    }
}
