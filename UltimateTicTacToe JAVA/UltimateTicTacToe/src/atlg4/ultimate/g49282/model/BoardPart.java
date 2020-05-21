package atlg4.ultimate.g49282.model;

/**
 * The interface for the miniTicTacToe and square.
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public interface BoardPart {

    /**
     * Allows to get the symbol.
     *
     * @return the symbol of my boardPart.
     */
    public Symbol getSymbol();

    /**
     * Allows to put a symbol in a square or a tictactoe.
     *
     * @param sym
     */
    public void setSymbol(Symbol sym);

    /**
     * Allows to know if the boardPart is empty.
     *
     * @return true if it's empty.
     */
    public boolean isEmpty();

}
