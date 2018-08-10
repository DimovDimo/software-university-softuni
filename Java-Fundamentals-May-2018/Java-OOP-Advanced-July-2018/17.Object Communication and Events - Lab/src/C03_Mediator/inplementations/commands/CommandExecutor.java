package C03_Mediator.inplementations.commands;

import C03_Mediator.interfaces.Command;
import C03_Mediator.interfaces.Executor;

public class CommandExecutor implements Executor {
    @Override
    public void executeCommand(Command command) {
        command.execute();
    }
}
