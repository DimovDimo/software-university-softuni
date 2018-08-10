package C03_Mediator.inplementations.commands;

import B02_Command.interfaces.Command;
import B02_Command.interfaces.Target;
import C03_Mediator.interfaces.AttackGroup;

public class GroupAttackCommand implements Command {
    private AttackGroup group;

    public GroupAttackCommand(AttackGroup group) {
        this.group = group;
    }

    @Override
    public void execute() {
        this.group.groupAttack();
    }
}
