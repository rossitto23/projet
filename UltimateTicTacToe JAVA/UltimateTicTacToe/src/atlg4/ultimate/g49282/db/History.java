package atlg4.ultimate.g49282.db;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Nicolas Rossitto <49282@etu.he2b.be>
 */
public class History {

    private final SimpleStringProperty player1;
    private final SimpleStringProperty player2;
    private final SimpleIntegerProperty wins;
    private final SimpleIntegerProperty loses;
    private final SimpleIntegerProperty draw;

    public History(String player1, String player2, int wins, int loses, int draw) {
        this.player1 = new SimpleStringProperty(player1);
        this.player2 = new SimpleStringProperty(player2);
        this.wins = new SimpleIntegerProperty(wins);
        this.loses = new SimpleIntegerProperty(loses);
        this.draw = new SimpleIntegerProperty(draw);
    }

    public String getPlayer1() {
        return this.player1.get();
    }

    public String getPlayer2() {
        return this.player2.get();
    }

    public int getWins() {
        return this.wins.get();
    }

    public int getLoses() {
        return this.loses.get();
    }

    public int getDraw() {
        return this.draw.get();
    }

}
