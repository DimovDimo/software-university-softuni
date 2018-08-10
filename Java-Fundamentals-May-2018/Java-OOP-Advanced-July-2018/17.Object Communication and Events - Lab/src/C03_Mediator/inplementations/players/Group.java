package C03_Mediator.inplementations.players;

import C03_Mediator.interfaces.AttackGroup;
import C03_Mediator.interfaces.Attacker;
import C03_Mediator.interfaces.Target;

import java.util.ArrayList;
import java.util.List;

public class Group implements AttackGroup {

    private List<Attacker> attackers;
    private Target target;

    public Group() {
        this.attackers = new ArrayList<>();
    }

    @Override
    public void addMember(Attacker attacker) {
        this.attackers.add(attacker);
    }

    @Override
    public void groupTarget(Target target) {
        this.target = target;
    }

    @Override
    public void groupAttack() {
        for (Attacker attacker : attackers) {
            attacker.setTarget(target);
            attacker.attack();
        }
    }
}
