package D04_Observer.inplementations.commands;

import B02_Command.interfaces.Command;
import B02_Command.interfaces.Target;
import D04_Observer.interfaces.AttackGroup;

public class GroupTargetCommand implements Command {
    private AttackGroup group;
    private Target target;

    public GroupTargetCommand(AttackGroup group, Target target) {
        this.group = group;
        this.target = target;
    }

    @Override
    public void execute() {
        this.group.groupTarget((D04_Observer.interfaces.Target) this.target);
    }
}
