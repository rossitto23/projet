package blokus_rossitto.viewFx;

import blokus_rossitto.model.Model;
import blokus_rossitto.model.Position;
import blokus_rossitto.util.Observer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class BoardGrid extends GridPane implements Observer {

    private MouseEvent mouse;

    public BoardGrid(Model game) {
        super();
        game.registerObserver(this);
        this.setPadding(new Insets(1));
        init(game);
    }

    private void init(Model game) {
        this.getChildren().clear();

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

                this.add(new SquareG(i, j, game), i, j);
            }
        }

        this.addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent event) -> {
            game.selectPiece(game.getSelectedPiece(), game.getCurrentPlayer());
            event.consume();
        });
    }

    /**
     *
     * @param game
     * @throws IllegalArgumentException
     */
    public void put(Model game) throws Exception {
        clearBoard(game);
        SquareG sg;

        if (game.getSelectedPiece() != null) {
            for (Node chil : this.getChildren()) {
                sg = (SquareG) chil;
                List<Position> l = game.positionLive();

                for (Position p : l) {
                    if (p.equals(new Position(sg.getXForSquare(), sg.getYForSquare()))) {
                        sg.colorSquare(game);
                    }
                }
            }
        }
    }

    @Override
    public void update(Model game) {

        try {
            put(game);
        } catch (Exception ex) {
            Logger.getLogger(BoardGrid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearBoard(Model game) {
        SquareG sg;
        for (Node chil : this.getChildren()) {
            sg = (SquareG) chil;
            sg.addColor(game);
        }
    }

}
