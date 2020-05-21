package blokus_rossitto.viewFx;

import blokus_rossitto.model.Model;
import blokus_rossitto.model.Piece;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareG extends Rectangle {

    private int x;
    private int y;

    public SquareG(int x, int y, Model game) {
        super(29, 29);
        this.x = x;
        this.y = y;
        this.setStroke(Color.TRANSPARENT);
        this.setStrokeWidth(1);

        addColor(game);

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, new ClickedAction(game, this));
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EnteredAction(game, this));

    }

    public final void addColor(Model game) {

        if (game.getBlokus().getPiece(x, y).equals(new Piece())) {
            if (x == 0 && y == 0) {
                this.setFill(Color.BLUE);
            } else if (x == 0 && y == 19) {
                this.setFill(Color.YELLOW);
            } else if (x == 19 && y == 19) {
                this.setFill(Color.RED);
            } else if (x == 19 && y == 0) {
                this.setFill(Color.GREEN);
            } else {
                this.setFill(Color.BLACK);
            }

        } else if (game.getBlokus().getPiece(x, y).getColor() != null) {
            this.setFill(choiceColor(game.getBlokus().getPiece(x, y).getColor().name()));

        }

    }

    public void colorSquare(Model game) throws Exception {
        if (game.verifyMove(game.getCurrentPosition())) {
            this.setFill(choiceColor(game.getCurrentPlayer().getColor().name()));
        } else {
            this.setFill(Color.GRAY);
        }
    }

    private Color choiceColor(String s) {
        Color c = null;

        switch (s) {
            case "BLUE":
                c = Color.BLUE;
                break;
            case "GREEN":
                c = Color.GREEN;
                break;
            case "RED":
                c = Color.RED;
                break;
            case "YELLOW":
                c = Color.YELLOW;
                break;
        }
        return c;
    }

    public int getXForSquare() {
        return x;
    }

    public int getYForSquare() {
        return y;
    }

}
