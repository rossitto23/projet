package atlg4.composant.g49282.model;

/**
 *
 * @author Nicolas Rossitto, 49282
 */
public class Player {

    private final Nameplayer symbol;

    public Player(Nameplayer symbol) {
        this.symbol = symbol;
    }

    /**
     * initialize the symbol at null
     */
    public Player() {
        this.symbol = null;
    }

    /**
     * return the symbol player
     *
     * @return symbol
     */
    public Nameplayer getSymbol() {
        return symbol;
    }
}
