package blokus_rossitto.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Piece {

    private int idPiece;
    private List<Position> shape = new ArrayList<>();
    private Colored color;

    /**
     * constructor of piece
     *
     * @param idPiece
     * @param positions
     * @param color
     */
    public Piece(Colored color, int idPiece, Position... positions) {
        this.idPiece = idPiece;
        addPos(positions);
        this.color = color;

    }

    public Piece(Colored color, int idPiece, List<Position> positions) {
        this.color = color;
        this.idPiece = idPiece;
        this.shape = positions;
    }

    /**
     * constructor of piece
     *
     * @param color
     * @param idPiece
     * @param position
     */
    public Piece(Colored color, int idPiece, Position position) {
        this.color = color;
        shape.add(position);
        this.idPiece = idPiece;

    }

    public Piece(Colored color, int idPiece) {
        this.color = color;
        this.idPiece = idPiece;
    }

    /**
     * initialize idPiece to 0 initialize the shape to null
     *
     */
    public Piece() {
        this.idPiece = 0;
        this.shape = null;
        this.color = null;
    }

    /**
     * add to shape, all positions
     *
     * @param positions
     */
    private void addPos(Position[] positions) {
        shape.addAll(Arrays.asList(positions));
    }

    /**
     * return the value of idPiece
     *
     * @return idPiece
     */
    public int getIdPiece() {
        return idPiece;
    }

    /**
     * return one copy of List of shape
     *
     * @return new lis of shape
     */
    public List<Position> getShape() {
        return new ArrayList<>(shape);
    }

    /**
     * turn piece with method mirror
     *
     * @return
     */
    public Piece turnPieceMirror() {
        List<Position> pieceTurn = new ArrayList<>();
        this.getShape().forEach((p) -> {
            p.mirror();
        });

        return new Piece(this.color, this.idPiece, pieceTurn);

    }

    /**
     * turn piece
     *
     * @return
     */
    public Piece turnPiece() {
        List<Position> pieceTurn = new ArrayList<>();
        this.getShape().forEach((p) -> {
            p.swap();
        });

        return new Piece(this.color, this.idPiece, pieceTurn);
    }

    /**
     * return the color
     *
     * @return color
     */
    public Colored getColor() {
        return color;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Piece other = (Piece) obj;
        if (this.idPiece != other.idPiece) {
            return false;
        }
        if (!Objects.equals(this.shape, other.shape)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ("" + idPiece);
    }

}
