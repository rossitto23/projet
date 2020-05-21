package blokus_rossitto.model;

public class FirstMove implements MakeMove {

    private Board board;

    /**
     * Constructor of First move
     *
     * @param board
     */
    public FirstMove(Board board) {
        this.board = board;
    }

    /**
     * check if we can put the piece to the position
     *
     * @param piece
     *
     *
     * @param position
     * @param currentPlayer
     * @return
     * @throws Exception
     */
    @Override
    public boolean canPut(Piece piece, Position position, Player currentPlayer) {

        boolean ok = false;

        if (position.equals(currentPlayer.getStartPosition())) {
            ok = new Move(board).canPut(piece, position, currentPlayer);
        }

        return ok;
    }

    /**
     * put the piece on board thanks to the setAt method
     *
     * @param piece
     * @param position
     * @throws Exception
     */
    @Override
    public void putPiece(Piece piece, Position position) throws Exception {

        for (int i = 0; i < piece.getShape().size(); i++) {
            setAt(position, piece.getShape().get(i), piece);
        }
    }

    private void setAt(Position position, Position piecePosition, Piece piece) {
        board.getBoard()[piecePosition.getX() + position.getX()][piecePosition.getY()
                + position.getY()] = new Piece(piece.getColor(), piece.getIdPiece());
    }

}
