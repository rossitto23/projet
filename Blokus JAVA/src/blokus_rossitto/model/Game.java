package blokus_rossitto.model;

import blokus_rossitto.util.Observer;
import java.util.ArrayList;
import java.util.List;

public class Game implements Model {

    private int currentPlayer;
    private final List<Player> players;
    private Board board;
    private Piece selectedPiece;
    private final List<Observer> observers;
    private Position currentPosition;
    private Position currentPosBot;
    private int endGame;
    

    /**
     * create new list of players and initialize the game
     */
    public Game() {
        players = new ArrayList<>(4);
        initGame();
        this.observers = new ArrayList<>();
        this.currentPlayer = 0;
        this.currentPosition = new Position(10, 10);
        this.endGame = 0;

    }

    /**
     * launch the initialize game
     */
    @Override
    public void start() {

        initGame();
        notifyObservers();

    }

    private void initGame() {
        this.board = new Board();

        this.currentPlayer = 0;
        this.selectedPiece = null;
    }

    /**
     * create 4players with as attributes: name, color and position add 4players
     * in the List
     *
     * @param player
     */
    public void addPlayers(Player player) {
        players.add(player);
        player.getStock().createsStock(player.getColor());
        player.getStockPieceRemove();

    }

    /**
     * return the precedentPiece
     *
     * @return precedentPiece
     */
    @Override
    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    /**
     * return a copy of the list
     *
     * @return
     */
    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    /**
     * pass to the next player
     */
    @Override
    public void nextPlayer() {
        this.currentPlayer = currentPlayer + 1;
        if (currentPlayer >= players.size()) {
            currentPlayer = 0;
        }

        if (getCurrentPlayer().getStock().getStock().isEmpty()) {
            nextPlayer();
        }
        endOfTheGame();
        if (getCurrentPlayer().isBot()) {
            System.out.println(playBot());
            playLap(currentPosBot);

        }

        notifyObservers();

    }

    /**
     * make the end of the game
     */
    @Override
    public boolean endOfTheGame() {
        return endGame == 4;
    }

    /**
     * return the current player in game
     *
     * @return players.get(currentPlayer)
     */
    @Override
    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    /**
     * the param p is the select piece if p is not null and the player equals
     * the current player
     *
     * @param p
     * @param player
     */
    @Override
    public void selectPiece(Piece p, Player player) {
        if (p != null && player.equals(this.getCurrentPlayer())) {
            selectedPiece = p;
            notifyObservers();
        }

    }

    /**
     * return the board
     *
     * @return board
     */
    @Override
    public Board getBlokus() {
        return board;
    }

    /**
     * put the piece selected in param in board pass to the next player
     *
     * @param piece
     * @param point
     * @param currentPlayer
     */
    @Override
    public void putPiece(Piece piece, Position point, Player currentPlayer) {
        this.getCurrentPlayer().getStock().remove(piece);
        this.getCurrentPlayer().addStock(piece);
        for (int i = 0; i < piece.getShape().size(); i++) {
            board.putPiece(piece, point);

        }

    }

    /**
     * turn the piece like a mirror
     */
    public void turnSelectedPieceMirror() {
        if (selectedPiece != null) {
            selectedPiece.turnPieceMirror();
            notifyObservers();
        }
    }

    /**
     * turn the piece a quarter of a turn
     */
    public void turnSelectedPiece() {
        if (selectedPiece != null) {
            selectedPiece.turnPiece();
            notifyObservers();
        }
    }

    /**
     * verify if the piece can put in the board return true if the piece can put
     * in the board, false else
     *
     * @param piece
     * @param point
     * @return
     */
    public boolean canPut(Piece piece, Position point) {
        boolean ok = true;
        int i = 0;
        while (i < piece.getShape().size() && ok) {
            ok = board.isInside(point.getX(), point.getY());
            i++;
        }
        return ok;
    }

    /**
     * return true if the position of piece is not in the board
     *
     * @param positionPiece
     * @param position
     * @return
     */
    private boolean notInside(Position positionPiece, Position position) {
        return !board.isInside(positionPiece.getX()
                + position.getX(), positionPiece.getY() + position.getY());

    }

    /**
     * verify move if the size of stock is 21, the move is firstMove else
     * classic move
     *
     * @param position
     * @return
     * @throws Exception
     */
    public boolean verifyMove(Position position) {
        MakeMove move = null;
        boolean ok;

        if (this.getCurrentPlayer().getStock().getStock().size() == 22) {
            move = new FirstMove(board);
        } else {
            move = new ClassicMove(board);
        }

        ok = move.canPut(selectedPiece, position, this.getCurrentPlayer());

        return ok;
    }

    @Override
    public void registerObserver(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
        }
    }

    @Override
    public void removeObserver(Observer obs) {

        if (observers.contains(obs)) {
            observers.remove(obs);
        }
    }

    @Override
    public void notifyObservers() {

        observers.forEach((obs) -> {
            obs.update(this);
        });
    }

    public void clear() {
        initGame();
        notifyObservers();
    }

    @Override
    public void stop() throws Exception {
        if (!players.isEmpty()) {
            if (this.currentPlayer == players.size() - 1) {
                players.remove(this.getCurrentPlayer());
                selectedPiece = null;
                currentPlayer = 0;
            } else {
                players.remove(this.getCurrentPlayer());
            }
        } else {
            throw new Exception("Game over");
        }
        notifyObservers();

    }

    /**
     *
     * @param position
     * @return
     * @throws Exception
     */
    @Override
    public boolean playLap(Position position) {
        boolean ok = verifyMove(position);
        if (ok) {
            updateGame(position);
            currentPosition = new Position(10, 10);

        }

        notifyObservers();
        selectedPiece = null;
        return ok;
    }

    private void updateGame(Position position) {

        new Move(board).putPiece(selectedPiece, position);
        updateScore(players.get(currentPlayer), selectedPiece.getShape().size());
        getCurrentPlayer().getStock().getStock().remove(selectedPiece);
        getCurrentPlayer().addStock(selectedPiece);
        updateScore(players.get(currentPlayer), selectedPiece.getShape().size());
        nextPlayer();
        endGame = 0;
        notifyObservers();

    }

    private void updateScore(Player player, int score) {
        player.addScore(score);
    }

    @Override
    public List<Position> positionLive() {
        List<Position> l = new ArrayList();

        if (selectedPiece != null) {
            selectedPiece.getShape().stream().forEach((Position pos) -> {
                l.add(new Position(currentPosition.getX() + pos.getX(),
                        currentPosition.getY() + pos.getY()));

            });
        }

        return l;
    }

    @Override
    public Position getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public void selectPosition(int x, int y) throws Exception {
        if (selectedPiece != null) {
            this.playLap(new Position(x, y));
        }
    }

    /**
     * change the current position
     *
     * @param x
     * @param y
     */
    @Override
    public void setCurrentPosition(int x, int y) {

        this.currentPosition = new Position(x, y);
    }

    /**
     * Allows to know if at the position is an empty square.
     *
     * @param position
     * @param point
     * @return true if the position is an empty square.
     */
    @Override
    public boolean validMove(Position position, Position point) {

        boolean valid = false;

        if (!notInside(position, point)) {
            valid = new Piece().equals(board.getBoard()[position.getX() + point.getX()][point.getY() + position.getY()]);
        }

        return valid;
    }

    /**
     *
     * looking for a valid position for the piece
     *
     * @param piece
     * @return
     * @throws Exception
     */
    private boolean goodPositionBot(Piece piece) {
        MakeMove move = new ClassicMove(board);

        if (getCurrentPlayer().getStock().getStock().size() == 21) {
            move = new FirstMove(board);
        }

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (move.canPut(piece, new Position(i, j), getCurrentPlayer())) {
                    if (getCurrentPlayer().isBot()) {
                        currentPosBot = new Position(i, j);
                        return true;
                    }
                    return true;
                }
            }
        }
        return true;
    }

    private boolean playBot() {
        List<Piece> stock = getCurrentPlayer().getStock().getStock();

        for (int i = 0; i < 10; i++) {
            selectPiece(stock.get((int) (Math.random() * (((stock.size() - 1) - 0) + 1) + 0)), getCurrentPlayer());

            for (int k = 0; k <= 1; k++) {
                for (int j = 0; j < 4; j++) {
                    if (goodPositionBot(selectedPiece)) {
                        this.endGame++;
                        return true;
                    }
                    turnSelectedPiece();
                }
                turnSelectedPieceMirror();
            }
        }
        return false;
    }

    /**
     * return the winner's name
     *
     * @return winner
     */
    @Override
    public String getWinner() {
        int max = 0;
        String winner = null;

        for (Player play : this.players) {
            if (play.getScore() > max) {
                max = play.getScore();
            }
        }
        for (Player play : this.players) {
            if (play.getScore() == max) {
                winner = play.getName();
            }
        }
        return winner;
    }

    /**
     * increments endGame +1.
     *
     * @return
     */
    @Override
    public int addPass() {
        return this.endGame++;
    }

}
