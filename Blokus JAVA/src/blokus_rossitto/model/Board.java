package blokus_rossitto.model;

/**
 * is the board in game. The board is one board on 2dimension
 *
 * @author nicolasrossitto
 */
public class Board {

    private static final int BOARD_SIZE = 20;

    private Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

    /**
     * create the board of the game
     */
    public Board() {

        for (Piece[] board : board) {
            for (int i = 0; i < board.length; i++) {
                board[i] = new Piece();
            }
        }

    }

    /**
     * return the position of board in board[x][y]
     *
     * @param x
     * @param y
     * @return board[x][y]
     */
    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    /**
     * return the size of board
     *
     * @return BOARD_SIZE is cst
     */
    public int getBoardSize() {
        return BOARD_SIZE;
    }

    /**
     * put piece at position
     *
     * @param piece
     * @param position
     */
    public void putPiece(Piece piece, Position position) {
        for (Position p : piece.getShape()) {
            board[position.getX() + p.getX()][position.getY() - p.getY()] = piece;
        }
    }

    /**
     * return true if x and y are greater than or equal to 0 and less than or
     * equal to 19 else false
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isInside(int x, int y) {
        return (x < BOARD_SIZE && x >= 0) && (y < BOARD_SIZE && y >= 0);
    }

    /**
     * return the board
     *
     * @return
     */
    public Piece[][] getBoard() {
        return board;
    }

}
