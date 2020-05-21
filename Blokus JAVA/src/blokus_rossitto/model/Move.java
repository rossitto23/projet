package blokus_rossitto.model;

public class Move implements MakeMove {

    private final Board board;

    /**
     * Constructor move
     *
     * @param board
     */
    public Move(Board board) {
        this.board = board;
    }

    /**
     * method checks if we can put the piece at the given position
     *
     * @param piece
     * @param position
     * @param currentPlayer
     * @return
     * @throws Exception
     */
    @Override
    public boolean canPut(Piece piece, Position position, Player currentPlayer) {
        boolean can = true;
        int i = 0;

        while (i < piece.getShape().size() && can) {
            can = validMove(piece.getShape().get(i), position);
            i++;
        }
        return can;
    }

    private boolean validMove(Position positionPiece, Position position) {
        boolean can = false;

        if (!notInside(positionPiece, position)) {
            can = new Piece().equals(board.getPiece(positionPiece.getX()
                    + position.getX(), positionPiece.getY() + position.getY()));
        }

        return can;
    }

    private boolean notInside(Position positionPiece, Position position) {
        return !board.isInside(positionPiece.getX() + position.getX(),
                positionPiece.getY() + position.getY());
    }

    /**
     * put the piece at position
     *
     * @param piece
     * @param position
     * @throws Exception
     */
    @Override
    public void putPiece(Piece piece, Position position) {
        for (int i = 0; i < piece.getShape().size(); i++) {
            setAt(position, piece.getShape().get(i), piece);
        }
    }

    private void setAt(Position position, Position positionPiece, Piece piece) {
        board.getBoard()[positionPiece.getX() + position.getX()][positionPiece.getY() + position.getY()] = new Piece(piece.getColor(), piece.getIdPiece());
    }

    private void remove(Piece piece, Position position) {
        for (Piece[] b : board.getBoard()) {
            for (int i = 0; i < board.getBoard()[0].length; i++) {
                if (b[i].equals(new Piece(piece.getColor(), piece.getIdPiece(), position))) {
                    b[i] = new Piece();
                }
            }
        }
    }

    void move(Position position, Position position2) {
        Piece piece = board.getPiece(position.getX(), position.getY());
        remove(piece, position);
        putPiece(piece, position2);
    }

}
