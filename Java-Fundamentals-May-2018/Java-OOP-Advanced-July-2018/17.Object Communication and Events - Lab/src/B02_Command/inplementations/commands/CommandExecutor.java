package B02_Command.inplementations.commands;

import B02_Command.interfaces.Command;
import B02_Command.interfaces.Executor;

public class CommandExecutor implements Executor {
    @Override
    public void executeCommand(Command command) {
        command.execute();
    }
}
