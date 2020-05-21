package atlg4.ultimate.g49282.model;

/**
 * This class represent one player in the game.
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public class Player {

    private Symbol symbol;
    private String name;

    /**
     * The constructor of player.
     *
     * @param symbol of the player.
     */
    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     *
     * @return the symbol of player.
     */
    public Symbol getSymbol() {
        return symbol;
    }

    /**
     *
     * @return the name of player.
     */
    public String getName() {
        return name;
    }
}
