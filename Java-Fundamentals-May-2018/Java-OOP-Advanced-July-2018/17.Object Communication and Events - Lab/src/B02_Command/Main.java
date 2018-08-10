package B02_Command;

import B02_Command.abstracts.Logger;
import B02_Command.inplementations.loggers.CombatLogger;
import B02_Command.inplementations.loggers.EventLogger;

public class Main {
    public static void main(String[] args) {
        Logger combatLog = new CombatLogger();
        Logger eventLog = new EventLogger();

        combatLog.setSuccessor(eventLog);

//        Attacker warrior = new Warrior("Gosho", 10, combatLog);
//        Target target = (Target) new Dragon("Dragon", 5, 10, combatLog);

//        warrior.setTarget(target);
//        warrior.attack();

//        Executor executor = new CommandExecutor();
//        Command targetCommand = new TargetCommand(warrior, target);
//        Command attackrgetCommand = new AttackCommand(warrior);
//
//        executor.executeCommand(targetCommand);
//        executor.executeCommand(attackrgetCommand);
    }
}
