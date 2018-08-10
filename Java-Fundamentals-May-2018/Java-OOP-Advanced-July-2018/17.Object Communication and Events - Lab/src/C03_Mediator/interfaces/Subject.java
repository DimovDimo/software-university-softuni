package C03_Mediator.interfaces;

public interface Subject {
    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObservers();
}
