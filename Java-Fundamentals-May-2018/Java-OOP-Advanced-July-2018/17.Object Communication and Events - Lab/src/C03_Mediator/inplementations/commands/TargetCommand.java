package C03_Mediator.inplementations.commands;

import C03_Mediator.interfaces.Attacker;
import C03_Mediator.interfaces.Command;
import C03_Mediator.interfaces.Target;

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
