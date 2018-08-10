package D04_Observer;

import D04_Observer.abstracts.Logger;
import D04_Observer.inplementations.commands.GroupAttackCommand;
import D04_Observer.inplementations.commands.GroupTargetCommand;
import D04_Observer.inplementations.loggers.CombatLogger;
import D04_Observer.inplementations.loggers.EventLogger;
import D04_Observer.inplementations.players.Dragon;
import D04_Observer.inplementations.players.Group;
import D04_Observer.inplementations.players.Warrior;
import D04_Observer.interfaces.AttackGroup;
import D04_Observer.interfaces.Attacker;
import D04_Observer.interfaces.Command;
import D04_Observer.interfaces.Target;

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
