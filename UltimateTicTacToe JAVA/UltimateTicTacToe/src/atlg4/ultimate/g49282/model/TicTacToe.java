package atlg4.ultimate.g49282.model;

/**
 * Is the interface of TicTacToe.
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public interface TicTacToe {

    /**
     * Allows to know if the tic tac toe is finished.
     *
     * @return true if it's finished.
     */
    public boolean isFinished();

    /**
     * Allows to play in a part of tic tac toe.
     *
     * @param partIndex the tic tac toe that we want play.
     * @param sym the symbol of player.
     */
    public void playpart(int partIndex, Symbol sym);

    /**
     * Allows to know if he can put the piece.
     *
     * @param partIndex the part of tic tac toe that he wants to play.
     * @return true if he can play.
     */
    public boolean canPlayPart(int partIndex);

    /**
     * Allows to get the winner of the ticTacToe.
     *
     * @return the symbol of winner.
     */
    public Symbol getWinner();

    /**
     * Allows to check if there is a winner in the row.
     *
     * @return true if there is a winner.
     */
    public boolean checkRow();

    /**
     * Allows to check if there is a winner in the diagonals.
     *
     * @return true if there is a winner.
     */
    public boolean checkDiag();

    /**
     * Allows to check if there is a winner in the column.
     *
     * @return true if there is a winner.
     */
    public boolean checkColumn();

    /**
     * Allows to know if the tic tac toe is full.
     *
     * @return true if this is full.
     */
    public boolean isFull();
}
