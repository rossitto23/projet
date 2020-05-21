package bmr_rossitto.model;

import bmr_rossitto.util.Observable;
import bmr_rossitto.util.Observer;
import java.util.ArrayList;
import java.util.List;

public class Bmr implements Observable {

    private double taille;
    private double poids;
    private int age;
    private boolean man;
    private double calories;
    private double activity;
    private double bmr;
    private final List<Observer> observers = new ArrayList<>();

    /**
     * return the value of taille
     *
     * @return taille
     */
    public double getTaille() {
        return taille;
    }

    /**
     * return the value of poids
     *
     * @return poids
     */
    public double getPoids() {
        return poids;
    }

    /**
     * return the value of age
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * return true or false true if is a man, false else
     *
     * @return man
     */
    public boolean isMan() {
        return man;
    }

    /**
     * return the value of calories
     *
     * @return calories
     */
    public double getCalories() {
        return calories;
    }

    /**
     * return the value of activity in StyleVie
     *
     * @return activity
     */
    public double getActivity() {
        return activity;
    }

    /**
     * return the value of bmr
     *
     * @return bmr
     */
    public double getBmr() {
        return bmr;
    }

    /**
     * change the value of taille
     *
     * @param taille
     */
    public void setTaille(double taille) {
        this.taille = taille;
    }

    /**
     * change the value of poids
     *
     * @param poids
     */
    public void setPoids(double poids) {
        this.poids = poids;
    }

    /**
     * change the value of age
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * change the value of man
     *
     * @param man
     */
    public void setMan(boolean man) {
        this.man = man;
    }

    /**
     * change the value of calories
     *
     * @param calories
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * change the value of activity
     *
     * @param activity
     */
    public void setActivity(double activity) {
        this.activity = activity;
    }

    /**
     * change the value of bmr
     *
     * @param bmr
     */
    public void setBmr(double bmr) {
        this.bmr = bmr;
    }

    /**
     * if poids or taille or age is negatif, IllegalArgumentException else
     * calculate bmr with the formula
     */
    public void calcul() {

        if (poids <= 0 || taille <= 0 || age <= 0) {
            throw new IllegalArgumentException("Data is not ok! ");
        }

        if (man) {
            bmr = 13.7 * poids + 5 * taille - 6.8 * age + 66;
        } else {
            bmr = 9.6 * poids + 1.8 * taille - 4.7 * age + 655;
        }

        calories = bmr * activity;

        notifyObservers();
    }

    /**
     * allows to add observers
     *
     * @param obs the observer we want add
     */
    @Override
    public void registerObserver(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
        }
    }

    /**
     * allows you to delete observers
     *
     * @param obs the observer we want delete
     */
    @Override
    public void removeObserver(Observer obs) {
        if (observers.contains(obs)) {
            observers.remove(obs);
        }
    }

    /**
     * allows to notify all observers
     */
    @Override
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }

}
