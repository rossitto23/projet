package blokus_rossitto.viewFx;

import blokus_rossitto.model.Game;
import blokus_rossitto.model.Model;
import blokus_rossitto.model.Player;
import blokus_rossitto.util.Observer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuBar extends javafx.scene.control.MenuBar implements Observer {

    Menu file = new Menu("File");
    MenuItem exit = new MenuItem("Exit");
    Menu histo = new Menu("historique");
    Model game = new Game();
    private GridPane grid = new GridPane();
    Stage stage;

    public MenuBar(Model game, MenuItem menu) {
        super();

        file.getItems().add(exit);
        histo.getItems().add(menu);

        exit.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });


        this.getMenus().setAll(file,histo);

    }

    public void histoJoueur(Stage stage) {
        VBox vbox = new VBox();

        TextField joueur1 = new TextField(lastAction(game, 0, "joueur1"));
        TextField joueur2 = new TextField(lastAction(game, 1, "joueur2"));
        TextField joueur3 = new TextField(lastAction(game, 2, "joueur3"));
        TextField joueur4 = new TextField(lastAction(game, 3, "joueur4"));

        grid.add(joueur1, 0, 0);
        grid.add(joueur2, 0, 1);
        grid.add(joueur3, 0, 2);
        grid.add(joueur4, 0, 3);

        Scene scene = new Scene(vbox, 500, 500);

    }

    public String lastAction(Model game, int player, String name) {
        String st = new String();
        st += name + ": ";
        for (int j = 0; j < game.getPlayers().get(player).getStockPieceRemove().size(); j++) {
            st +="Piece ";
            st += game.getPlayers().get(player).getStockPieceRemove().get(j).getIdPiece();
            st += ("\n");
        }
        return st;
    }

    @Override
    public void update(Model game) {
        grid.getChildren().clear();
        
        histoJoueur(stage);
    }

}
