package blokus_rossitto.model;

import blokus_rossitto.util.Observable;
import java.util.List;

public interface Model extends Observable {

    void start();

    boolean endOfTheGame();

    Player getCurrentPlayer();

    void selectPiece(Piece p, Player player);

    void selectPosition(int x, int y) throws Exception;

    Board getBlokus();

    Piece getSelectedPiece();

    List<Player> getPlayers();

    void putPiece(Piece piece, Position point, Player currentPlayer);

    void clear();

    void nextPlayer();

    void stop() throws Exception;

    List<Position> positionLive();

    boolean verifyMove(Position position) throws Exception;

    Position getCurrentPosition();

    void setCurrentPosition(int x, int y);

    void turnSelectedPiece();

    boolean playLap(Position position) throws Exception;

    boolean validMove(Position position, Position point);

    void turnSelectedPieceMirror();

    void addPlayers(Player player);

    String getWinner();

    int addPass();
}
