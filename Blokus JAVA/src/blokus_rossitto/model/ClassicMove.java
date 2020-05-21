package blokus_rossitto.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClassicMove implements MakeMove {

    private Board board;

    /**
     * Constructor of classic move
     *
     * @param board
     */
    public ClassicMove(Board board) {
        this.board = board;
    }

    /**
     *
     * method checks if we can place the piece
     *
     * @param piece
     * @param position
     * @param currentPlayer
     * @return
     * @throws Exception
     */
    @Override
    public boolean canPut(Piece piece, Position position, Player currentPlayer) {
        List<Position> start = getValidStart(piece, position);
        List<Position> other = getOthersPosition(piece, position);
        boolean checkOther = checkOther(currentPlayer, other);
        boolean checkStart = checkStart(currentPlayer, start);
        boolean b = new Move(board).canPut(piece, position, currentPlayer);

        return b && checkOther && checkStart;
    }

    /**
     *
     * method that puts the piece
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

    private void setAt(Position position, Position positionPiece, Piece piece) {
        board.getBoard()[positionPiece.getX() + position.getX()][positionPiece.getY()
                + position.getY()] = new Piece(piece.getColor(), piece.getIdPiece());
    }

    private List<Position> getValidStart(Piece piece, Position position) {
        List<Position> starts = new ArrayList<>();
        for (Position p : piece.getShape()) {
            starts.addAll(getStarts(p, position));
        }

       
        return starts;
    }

    private List<Position> getOthersPosition(Piece piece, Position position) {
        List<Position> pos = new ArrayList<>();
        for (Position posPiece : piece.getShape()) {
            List<Position> otherPos = getOthers(posPiece, position);
            pos.addAll(otherPos);
        }
       
        return pos;
    }

    private List<Position> getOthers(Position positionPiece, Position position) {
        Position p1 = new Position(positionPiece.getX() + position.getX(), positionPiece.getY() + position.getY() - 1);
        Position p2 = new Position(positionPiece.getX() + position.getX(), positionPiece.getY() + position.getY() + 1);
        Position p3 = new Position(positionPiece.getX() + position.getX() - 1, positionPiece.getY() + position.getY());
        Position p4 = new Position(positionPiece.getX() + position.getX() + 1, positionPiece.getY() + position.getY());

        return addValidPosition(p1, p2, p3, p4);
    }

    private boolean checkOther(Player currentPlayer, List<Position> other) {
        int i = 0;
        boolean good = true;

        while (i < other.size() && good) {
            Position position = other.get(i);
            Colored color = board.getBoard()[position.getX()][position.getY()].getColor();
            if (color != null) {
                good = color != currentPlayer.getColor();
            }
            i++;
        }
        return good;
    }

    private boolean checkStart(Player currentPlayer, List<Position> start) {
        boolean ok = checkNullStart(start);
        int i = 0;
        int goodStart = 0;

        if (ok) {
            while (i < start.size() && ok) {
                Position position = start.get(i);
                goodStart = check(goodStart, position, currentPlayer);
                i++;
            }
        }

        return goodStart >= 1;
    }

    private boolean checkNullStart(List<Position> starts) {
        int cpt = 0;
        for (Position start : starts) {
            if (this.board.getBoard()[start.getX()][start.getY()].getColor() == null) {
                cpt++;
            }
        }
        return cpt != starts.size();
    }

    private int check(int goodStart, Position position, Player currentPlayer) {
        Colored color = board.getBoard()[position.getX()][position.getY()].getColor();
        if (color != null) {
            if (color == currentPlayer.getColor()) {
                goodStart++;
            }
        }

        return goodStart;
    }

    private List<Position> addValidPosition(Position... positions) {
        List<Position> positionList = new ArrayList<>();
        for (Position positionPiece : positions) {
            if (board.isInside(positionPiece.getX(), positionPiece.getY())) {
                positionList.add(positionPiece);
            }
        }
        return positionList;
    }


    private Collection<? extends Position> getStarts(Position p, Position position) {
        Position s1 = new Position(position.getX() - 1 + p.getX(), position.getY() - 1 + p.getY());
        Position s2 = new Position(position.getX() - 1 + p.getX(), position.getY() + 1 + p.getY());
        Position s3 = new Position(position.getX() + 1 + p.getX(), position.getY() - 1 + p.getY());
        Position s4 = new Position(position.getX() + 1 + p.getX(), position.getY() + 1 + p.getY());

        return addValidPosition(s1, s2, s3, s4);

    }

}
