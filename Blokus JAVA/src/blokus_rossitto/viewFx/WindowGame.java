package blokus_rossitto.viewFx;

import blokus_rossitto.model.Model;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowGame extends BorderPane {

    History hist;

    public WindowGame(Model game, Button btn,Stage stage) {
        super();

        MenuItem historique = new MenuItem("Historique");

        BoardGrid bg = new BoardGrid(game);
        PlayersPane pp = new PlayersPane(game);
        Option option = new Option(game, pp, btn);
        MenuBar menu = new MenuBar(game, historique);
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox,500,500);

        this.setLeft(pp);
        this.setRight(bg);
        this.setBottom(option);
        this.setTop(menu);

        historique.setOnAction((ActionEvent event) -> {
            hist = new History(game);
            vbox.getChildren().add(hist);
            stage.setScene(scene);

        });
    }

}
