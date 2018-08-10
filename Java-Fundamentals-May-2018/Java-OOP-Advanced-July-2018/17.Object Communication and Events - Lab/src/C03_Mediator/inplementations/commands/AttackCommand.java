package C03_Mediator.inplementations.commands;

import C03_Mediator.interfaces.Attacker;
import C03_Mediator.interfaces.Command;

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
