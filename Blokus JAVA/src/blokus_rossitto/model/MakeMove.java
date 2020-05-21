package blokus_rossitto.model;

public interface MakeMove {

    /**
     * this method checks whether or not a piece can be put in a position.
     * return true if it's possible, false otherwise
     *
     * @param piece
     * @param position
     * @param currentPlayer
     * @return
     * @throws Exception
     */
    boolean canPut(Piece piece, Position position, Player currentPlayer);

    /**
     * this method put the piece at the position
     *
     * @param piece
     * @param position
     * @param currentPlayer
     * @throws Exception
     */
    void putPiece(Piece piece, Position position) throws Exception;
}
