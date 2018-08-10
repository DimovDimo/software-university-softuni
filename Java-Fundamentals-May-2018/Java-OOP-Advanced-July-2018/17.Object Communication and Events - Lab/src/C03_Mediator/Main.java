package C03_Mediator;

import C03_Mediator.abstracts.Logger;
import C03_Mediator.inplementations.commands.GroupAttackCommand;
import C03_Mediator.inplementations.commands.GroupTargetCommand;
import C03_Mediator.inplementations.loggers.CombatLogger;
import C03_Mediator.inplementations.loggers.EventLogger;
import C03_Mediator.inplementations.players.Dragon;
import C03_Mediator.inplementations.players.Group;
import C03_Mediator.inplementations.players.Warrior;
import C03_Mediator.interfaces.AttackGroup;
import C03_Mediator.interfaces.Attacker;
import C03_Mediator.interfaces.Command;
import C03_Mediator.interfaces.Target;

public class Main {
    public static void main(String[] args) {
        Logger combatLog = new CombatLogger();
        Logger eventLog = new EventLogger();

        combatLog.setSuccessor(eventLog);

        Attacker warrior = new Warrior("Gosho1", 10, combatLog);
        Attacker warrior1 = new Warrior("Gosho2", 10, combatLog);
        Attacker warrior2 = new Warrior("Gosho3", 10, combatLog);
        Attacker warrior3 = new Warrior("Gosho4", 10, combatLog);
        Target target = (Target) new Dragon("Dragon", 40, 10, combatLog);

        AttackGroup group = new Group();
        group.addMember(warrior);
        group.addMember(warrior1);
        group.addMember(warrior2);
        group.addMember(warrior3);
//        group.groupTarget(target);
//        group.groupAttack();

        Command groupTargetCommand = (Command) new GroupTargetCommand(group, (B02_Command.interfaces.Target) target);
        Command groupAttackCommand = (Command) new GroupAttackCommand(group);

        groupTargetCommand.execute();
        groupAttackCommand.execute();
    }
}
