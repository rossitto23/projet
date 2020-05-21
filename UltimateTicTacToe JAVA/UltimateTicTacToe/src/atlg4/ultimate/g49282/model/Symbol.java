package atlg4.ultimate.g49282.model;

/**
 * This class represent the symbol of player.
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public enum Symbol {
    X("X"),
    O("O");

    public String value;

    /**
     * Allows to transform the enum in string.
     *
     * @param value
     */
    private Symbol(String value) {
        this.value = value;
    }

    /**
     * Allows to get the value of symbol.
     *
     * @return the value.
     */
    public String getValue() {
        return value;
    }
}
