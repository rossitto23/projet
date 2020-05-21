package blokus_rossitto.viewFx;

import blokus_rossitto.model.Model;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class EnteredAction implements EventHandler<MouseEvent> {

    private final Model game;
    private final SquareG sg;

    /**
     * constructor of enteredAction
     *
     * @param game
     * @param sg
     */
    public EnteredAction(Model game, SquareG sg) {
        this.game = game;
        this.sg = sg;
    }

    @Override
    public void handle(MouseEvent event) {

        if (!game.getPlayers().isEmpty()) {
            game.setCurrentPosition(sg.getXForSquare(), sg.getYForSquare());
        }
        event.consume();
    }

}
