package blokus_rossitto.viewFx;

import blokus_rossitto.model.Model;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickedAction implements EventHandler<MouseEvent> {

    private Model game;
    private SquareG sg;

    /**
     * constructor of clicked action
     *
     * @param game
     * @param sg
     */
    public ClickedAction(Model game, SquareG sg) {
        this.game = game;
        this.sg = sg;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {

            try {
                game.selectPosition(sg.getXForSquare(), sg.getYForSquare());
            } catch (Exception ex) {
                Logger.getLogger(ClickedAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event.isSecondaryButtonDown() && game.getSelectedPiece() != null) {
            game.turnSelectedPiece();
        }

        event.consume();
    }

}
