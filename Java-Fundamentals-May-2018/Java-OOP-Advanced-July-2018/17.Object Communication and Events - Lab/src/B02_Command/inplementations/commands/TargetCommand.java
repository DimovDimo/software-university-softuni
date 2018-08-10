package B02_Command.inplementations.commands;

import B02_Command.interfaces.Attacker;
import B02_Command.interfaces.Command;
import B02_Command.interfaces.Target;

public class TargetCommand implements Command {
    private Attacker attacker;
    private Target target;

    public TargetCommand(Attacker attacker, Target target) {
        this.attacker = attacker;
        this.target = target;
    }

    @Override
    public void execute() {
        this.attacker.setTarget(target);
    }
}
