package atlg4.composant.g49282.model;

/**
 * enumeration of the team, two team: X and O
 *
 * @author Nicolas Rossitto, 49282
 */
public enum Nameplayer {
    JOUEUR_X("X"),
    JOUEUR_O("O");
    private final String value;

    private Nameplayer(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
