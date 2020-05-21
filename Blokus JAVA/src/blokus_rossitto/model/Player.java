package blokus_rossitto.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private Colored color;
    private Stock stock;
    private int score;
    private int idPlayer;
    private Position startPosition;
    private boolean bot;
    private List<Piece> stockPieceRemove;

    public Player(String name, Colored color, int idPlayer, Position pos) {
        this.color = color;
        stock = new Stock(color);
        this.score = 0;
        this.name = name;
        this.idPlayer = idPlayer;
        this.startPosition = pos;
        this.bot = false;
        stockPieceRemove = new ArrayList<>();

        stock.createsStock(color);

    }

    /**
     * return the color of color
     *
     * @return color
     */
    public Colored getColor() {
        return color;
    }

    /**
     * return the stock
     *
     * @return stock
     */
    public Stock getStock() {
        return stock;
    }
    
    public List<Piece> getStockPieceRemove(){
        return stockPieceRemove;
    }
    
    void addStock(Piece piece){
        this.stockPieceRemove.add(piece);
    }

    /**
     * return the value of score
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    /**
     * modifies the score
     *
     * @param score
     */
    void addScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score is not correct!");
        } else {
            this.score += score;
        }
    }

    public String getName() {
        return name;
    }

    public boolean isBot() {
        return bot;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + '}';
    }

}
