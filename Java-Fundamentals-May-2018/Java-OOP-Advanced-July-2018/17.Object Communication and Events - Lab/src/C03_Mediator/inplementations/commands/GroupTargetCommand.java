package C03_Mediator.inplementations.commands;

import B02_Command.interfaces.Command;
import B02_Command.interfaces.Target;
import C03_Mediator.interfaces.AttackGroup;

public class GroupTargetCommand implements Command {
    private AttackGroup group;
    private Target target;

    public GroupTargetCommand(AttackGroup group, Target target) {
        this.group = group;
        this.target = target;
    }

    @Override
    public void execute() {
        this.group.groupTarget((C03_Mediator.interfaces.Target) this.target);
    }
}
