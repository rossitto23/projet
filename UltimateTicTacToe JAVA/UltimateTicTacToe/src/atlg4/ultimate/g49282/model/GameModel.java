package atlg4.ultimate.g49282.model;

import java.util.List;

/**
 *
 * @author Nicolas Rossitto <49282@etu.he2b.be>
 */
public interface GameModel {

    /**
     * Allows to know if the game is Over.
     *
     * @return true if it's over.
     */
    public void isGameOver();

    /**
     * Allows to get the current player.
     *
     * @return the current player.
     */
    public Player getCurrentPlayer();

    /**
     * Allows to pass at the next player.
     */
    public void nextPlayer();

    /**
     * Allows to the current player that give up the game.
     */
    public void giveUp();

    /**
     * Allows to put a symbol in a square.
     *
     * @param partUTT the Mini tic tac toe that he wants to play.
     * @param partMTT the square that he wants to play.
     */
    public void putSymbol(int partUTT, int partMTT);

    /**
     * Allows to get the current Ultimate Tic Tac Toe.
     *
     * @return The current ultimate tic tac toe.
     */
    UltimateTicTacToe getUtt();

    /**
     * Allows to get the winner(s) of the game.
     *
     * @return the list of winner(s).
     */
    List<Player> getWinnerOfTheGame();

}
