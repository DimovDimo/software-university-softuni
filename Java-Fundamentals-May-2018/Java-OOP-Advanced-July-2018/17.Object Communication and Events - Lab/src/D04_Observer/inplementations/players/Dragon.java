package D04_Observer.inplementations.players;

import D04_Observer.abstracts.Logger;
import D04_Observer.interfaces.Observer;
import D04_Observer.interfaces.Subject;
import D04_Observer.interfaces.Target;

import java.util.List;

public class Dragon implements Target, Subject {

    private static final String THIS_DIED_EVENT = "%s dies";

    private String id;
    private int hp;
    private int reward;
    private boolean eventTriggered;
    private Logger logger;
    private List<Observer> observers;

    public Dragon(String id, int hp, int reward, Logger logger) {
        this.id = id;
        this.hp = hp;
        this.reward = reward;
        this.logger = logger;
    }

    @Override
    public void receiveDamage(int dmg) {
        if (!this.isDead()) {
            this.hp -= dmg;
        }

        if (this.isDead() && !eventTriggered) {
            System.out.println(String.format(THIS_DIED_EVENT, this));
            this.eventTriggered = true;
        }
    }

    @Override
    public boolean isDead() {
        return this.hp <= 0;
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public void register(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach(o -> o.update(reward));
    }
}
