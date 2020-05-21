package blokus_rossitto.viewFx;

import blokus_rossitto.model.Model;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Option extends HBox {

    private PlayersPane playerPane;
    private Model game;

    public Option(Model game, PlayersPane playerPane, Button btn) {
        super();

        this.playerPane = playerPane;
        this.getChildren().addAll(btn, initSkipTurn(game),
                initStop(game), initTurn(game), initMirror(game));
        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setPadding(new Insets(2));
        this.setSpacing(20);
    }

    private Button initSkipTurn(Model game) {
        Button skipTurn = new Button("Je passe");
        skipTurn.setOnAction((ActionEvent event) -> {
            game.addPass();
            game.nextPlayer();
        });

        return skipTurn;
    }

    private Button initStop(Model game) {
        Button stop = new Button("J'arrête");
        stop.setOnAction((ActionEvent event) -> {
            try {
                game.stop();
            } catch (Exception ex) {
                Logger.getLogger(Option.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return stop;
    }

    private Button initTurn(Model game) {
        Button turn = new Button("Tourner");
        turn.setOnAction((ActionEvent event) -> {
            game.turnSelectedPiece();
        });
        return turn;
    }

    private Button initMirror(Model game) {
        Button mirror = new Button("Miroir");
        mirror.setOnAction((ActionEvent event) -> {
            game.turnSelectedPieceMirror();
        });

        return mirror;
    }

}
