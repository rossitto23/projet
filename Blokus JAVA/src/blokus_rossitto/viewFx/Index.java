package blokus_rossitto.viewFx;

import blokus_rossitto.model.Bot;
import blokus_rossitto.model.Colored;
import blokus_rossitto.model.Model;
import blokus_rossitto.model.Player;
import blokus_rossitto.model.Position;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Index extends GridPane {

    private static final int POS_MAX = 19;
    private static final int POS_MIN = 0;

    private final Label joueur1 = new Label("joueur1");
    private final Label joueur2 = new Label("joueur2");
    private final Label joueur3 = new Label("joueur3");
    private final Label joueur4 = new Label("joueur4");

    private final TextField j1 = new TextField();
    private final TextField j2 = new TextField();
    private final TextField j3 = new TextField();
    private final TextField j4 = new TextField();

    private final Button start = new Button("Commencer la partie! ");

    public Index(Model game, Stage primaryStage, Button btn) {
        start.setVisible(true);
        super.setAlignment(Pos.CENTER_LEFT);
        super.setPadding(new Insets(20));
        super.setHgap(10);
        super.setVgap(15);
        VBox vbox = new VBox();
        initJoueur();

        Scene scene = new Scene(vbox, 1000, 750);

        this.add(start, 0, 10);

        start.setOnAction(e -> {
            addPlay(j1, j2, j3, j4, game);
            WindowGame win = new WindowGame(game, btn,primaryStage);
            vbox.getChildren().add(win);
            primaryStage.setScene(scene);
        });

    }

    private void initJoueur() {
        joueur1.setTextFill(Color.BLACK);
        joueur2.setTextFill(Color.BLACK);
        joueur3.setTextFill(Color.BLACK);
        joueur4.setTextFill(Color.BLACK);

        add(joueur1, 1, 1);
        add(joueur2, 1, 2);
        add(joueur3, 1, 3);
        add(joueur4, 1, 4);

        add(j1, 2, 1);
        add(j2, 2, 2);
        add(j3, 2, 3);
        add(j4, 2, 4);

    }

    private void addPlayer(TextField play, Model game) {
        switch (game.getPlayers().size()) {
            case 0:
                Player play1 = new Player(play.getText(), Colored.BLUE, 1, new Position(POS_MIN, POS_MIN));
                game.addPlayers(play1);
                break;
            case 1:
                Player play2 = new Player(play.getText(), Colored.YELLOW, 2, new Position(POS_MIN, POS_MAX));
                game.addPlayers(play2);
                break;
            case 2:
                Player play3 = new Player(play.getText(), Colored.RED, 3, new Position(POS_MAX, POS_MAX));
                game.addPlayers(play3);
                break;
            case 3:

                Player p4 = new Player(play.getText(), Colored.GREEN, 4, new Position(POS_MAX, POS_MIN));
                game.addPlayers(p4);
                break;
        }
    }

    private void addPlay(TextField play, TextField play2, TextField play3, TextField play4, Model game) {
        if (!play.getText().isEmpty()) {
            addPlayer(play, game);
        } else {
            Player bot = new Bot("Bot1", Colored.BLUE, 1, new Position(POS_MIN, POS_MIN), true);
            game.addPlayers(bot);
        }
        if (!play2.getText().isEmpty()) {
            addPlayer(play2, game);
        } else {
            Player bot2 = new Bot("Bot2", Colored.YELLOW, 2, new Position(POS_MIN, POS_MAX), true);
            game.addPlayers(bot2);
        }
        if (!play3.getText().isEmpty()) {
            addPlayer(play3, game);
        } else {
            Player bot3 = new Bot("Bot3", Colored.RED, 3, new Position(POS_MAX, POS_MAX), true);
            game.addPlayers(bot3);
        }
        if (!play4.getText().isEmpty()) {
            addPlayer(play4, game);
        } else {
            Player bot4 = new Bot("Bot4", Colored.GREEN, 4, new Position(POS_MAX, POS_MIN), true);
            game.addPlayers(bot4);
        }

    }

}
