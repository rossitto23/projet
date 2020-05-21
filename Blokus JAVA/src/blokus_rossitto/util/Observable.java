package blokus_rossitto.util;

public interface Observable {

    void registerObserver(Observer obs);

    void removeObserver(Observer obs);

    void notifyObservers();
}
