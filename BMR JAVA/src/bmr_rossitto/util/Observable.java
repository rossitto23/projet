package bmr_rossitto.util;

public interface Observable {

    /**
     * allows to add observers
     *
     * @param obs the observers we want to add
     */
    public void registerObserver(Observer obs);

    /**
     * allows to delete observers
     *
     * @param obs the observers we want to delete
     */
    public void removeObserver(Observer obs);

    /**
     * allows to notify all observers
     */
    public void notifyObservers();
}
