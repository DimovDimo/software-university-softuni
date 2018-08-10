package D04_Observer.inplementations.commands;

import B02_Command.interfaces.Command;
import D04_Observer.interfaces.AttackGroup;

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
