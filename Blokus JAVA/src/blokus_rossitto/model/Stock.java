package blokus_rossitto.model;

import java.util.ArrayList;
import java.util.List;

public final class Stock {

    private final List<Piece> stock;
   

    /**
     * Create the stock
     *
     * @param color
     */
    public Stock(Colored color) {
        this.stock = new ArrayList<>();
        
        createsStock(color);

    }

    /**
     * create stock with new piece
     *
     * @param color
     */
    public void createsStock(Colored color) {
        stock.clear();

        stock.add(new Piece(color, 1, Pos(0, 0)));
        stock.add(new Piece(color, 2, Pos(0, 0), Pos(0, 1)));
        stock.add(new Piece(color, 3, Pos(0, 0), Pos(0, 1), Pos(0, 2)));
        stock.add(new Piece(color, 4, Pos(0, 0), Pos(0, 1), Pos(1, 1)));
        stock.add(new Piece(color, 5, Pos(0, 0), Pos(0, 1), Pos(0, 2), Pos(0, 3)));
        stock.add(new Piece(color, 6, Pos(1, 0), Pos(1, 1), Pos(0, 2), Pos(1, 2)));
        stock.add(new Piece(color, 7, Pos(0, 0), Pos(0, 1), Pos(1, 1), Pos(0, 2)));
        stock.add(new Piece(color, 8, Pos(0, 0), Pos(0, 1), Pos(1, 0), Pos(1, 1)));
        stock.add(new Piece(color, 9, Pos(0, 0), Pos(1, 0), Pos(1, 1), Pos(2, 1)));
        stock.add(new Piece(color, 10, Pos(0, 0), Pos(0, 1), Pos(0, 2), Pos(0, 3), Pos(0, 4)));
        stock.add(new Piece(color, 11, Pos(1, 0), Pos(1, 1), Pos(1, 2), Pos(1, 3), Pos(0, 3)));
        stock.add(new Piece(color, 12, Pos(0, 2), Pos(0, 3), Pos(1, 0), Pos(1, 1), Pos(1, 2)));
        stock.add(new Piece(color, 13, Pos(0, 1), Pos(0, 2), Pos(1, 0), Pos(1, 1), Pos(1, 2)));
        stock.add(new Piece(color, 14, Pos(0, 0), Pos(0, 2), Pos(1, 0), Pos(1, 1), Pos(1, 2)));
        stock.add(new Piece(color, 15, Pos(0, 0), Pos(0, 1), Pos(0, 2), Pos(0, 3), Pos(1, 1)));
        stock.add(new Piece(color, 16, Pos(0, 2), Pos(1, 0), Pos(1, 1), Pos(1, 2), Pos(2, 2)));
        stock.add(new Piece(color, 17, Pos(0, 0), Pos(0, 1), Pos(0, 2), Pos(1, 2), Pos(2, 2)));
        stock.add(new Piece(color, 18, Pos(0, 0), Pos(1, 0), Pos(1, 1), Pos(2, 1), Pos(2, 2)));
        stock.add(new Piece(color, 19, Pos(0, 0), Pos(0, 1), Pos(1, 1), Pos(2, 1), Pos(2, 2)));
        stock.add(new Piece(color, 20, Pos(0, 0), Pos(0, 1), Pos(1, 1), Pos(1, 2), Pos(2, 1)));
        stock.add(new Piece(color, 21, Pos(0, 1), Pos(1, 0), Pos(1, 1), Pos(1, 2), Pos(2, 1)));
        stock.add(new Piece(color, 22, Pos(0, 0), Pos(0, 1), Pos(1, 0), Pos(1, 1), Pos(0, 2), Pos(1, 2)));
    }

    private static Position Pos(int x, int y) {
        return new Position(x, y);
    }

    /**
     * return the stock
     *
     * @return stock
     */
    public List<Piece> getStock() {
        return stock;
    }

    /**
     * return true if the stock is empty, false otherwise
     *
     * @return
     */
    public boolean isEmpty() {
        return this.stock.isEmpty();
    }

    /**
     * remove the piece of stock
     *
     * @param piece
     */
    void remove(Piece piece) {
        this.stock.remove(piece);
    }
    

    /**
     *
     * if the idPiece of the pieceChoose of the stock is equal to the idPiece
     * placed in parameter, piece takes the value of pieceChoose
     *
     * @param idPiece
     * @return
     */
    public Piece getPiece(int idPiece) {
        Piece piece = null;

        for (Piece pieceChoose : stock) {
            if (pieceChoose.getIdPiece() == idPiece) {
                piece = pieceChoose;
            }
        }

        return piece;

    }

    @Override
    public String toString() {
        return "stock= " + stock + '}';
    }
}
