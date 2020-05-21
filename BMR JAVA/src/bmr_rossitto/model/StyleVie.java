package bmr_rossitto.model;

public enum StyleVie {

    SEDENTAIRE("sedentaire", 1.2),
    PEU_ACTIF("peu actif", 1.375),
    ACTIF("actif", 1.55),
    FORT_ACTIF("fort actif", 1.725),
    EXTREMEMENT_ACTIF("extremement actif", 1.9);
    private final String name;
    private final double besoin;

    private StyleVie(String value, double val) {
        this.name = value;
        this.besoin = val;
    }

    /**
     * return the name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * return the value of besoin
     *
     * @return besoin
     */
    public double getValue() {
        return besoin;
    }

    @Override
    public String toString() {
        return name;
    }

}
