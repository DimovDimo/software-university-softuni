package B02_Command.inplementations.commands;

import B02_Command.interfaces.Attacker;
import B02_Command.interfaces.Command;
import B02_Command.interfaces.Target;

public class AttackCommand implements Command {
    private Attacker attacker;

    public AttackCommand(Attacker attacker) {
        this.attacker = attacker;
    }

    @Override
    public void execute() {
        this.attacker.attack();
    }
}
