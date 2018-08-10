package D04_Observer.inplementations.players;

import D04_Observer.interfaces.AttackGroup;
import D04_Observer.interfaces.Attacker;
import D04_Observer.interfaces.Target;

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
