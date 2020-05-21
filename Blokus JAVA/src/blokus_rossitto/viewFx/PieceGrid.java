package blokus_rossitto.viewFx;

import blokus_rossitto.model.Model;
import blokus_rossitto.model.Piece;
import blokus_rossitto.model.Player;
import blokus_rossitto.model.Position;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PieceGrid extends GridPane {

    private Piece piece;
    private Player player;

    public PieceGrid(Piece piece, Model game, Player player) {
        super();
        this.piece = piece;
        this.player = player;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                addSquare(i, j);
            }
            this.setPadding(new Insets(1));
        }
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {

            if (game.getCurrentPlayer().equals(player)) {
                game.selectPiece(piece, player);
            }
            event.consume();
        });
    }

    public PieceGrid() {
        super();

    }

    private void addSquare(int x, int y) {
        Rectangle rec = new Rectangle(9, 9);

        if (piece.getShape().contains(new Position(x, y))) {
            rec.setFill(choiceColor(player.getColor().name()));

        } else {

            rec.setFill(Color.WHITE);
        }
        rec.setStroke(Color.BLACK);
        rec.setStrokeWidth(1);
        this.add(rec, x, y);
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
}
