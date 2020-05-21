package blokus_rossitto.viewFx;

import blokus_rossitto.model.Model;
import blokus_rossitto.model.Piece;
import blokus_rossitto.model.Player;
import blokus_rossitto.util.Observer;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ListPiece extends VBox implements Observer {

    private Player player;
    private GridPane grid = new GridPane();
    private int idPlayer;

    public ListPiece(int idPlayer, Model game) {
        super();

        player = game.getPlayers().get(idPlayer);
        this.idPlayer = idPlayer;

        Label pInfo = new Label(infoGame(game));
        this.getChildren().addAll(pInfo);

        game.registerObserver(this);
        fillPiece(game);
        grid.setHgap(1);
        this.getChildren().add(grid);
    }

    private String infoGame(Model game) {

        String textPlayer = new String();
        textPlayer += "Nom: ";
        textPlayer += player.getName();
        textPlayer += "   Score:  ";
        textPlayer += Double.toString(player.getScore());
        textPlayer += "  Pieces:  ";
        textPlayer += player.getStock().getStock().size();
        textPlayer += stockPiece(game);

        return textPlayer;
    }

    private String stockPiece(Model game) {
        String stockJoueur = new String();
        stockJoueur += "Nombre de pieces restantes: ";
        stockJoueur += player.getStock().getStock().size();

        return stockJoueur;
    }

    /**
     * place the piece on the grid.
     *
     * @param game
     */
    private void fillPiece(Model game) {
        for (int i = 0; i < player.getStock().getStock().size(); i++) {
            Piece piece = player.getStock().getStock().get(i);
            PieceGrid pg = new PieceGrid(piece, game, player);
            grid.add(pg, i % 7, i / 7);

        }
    }

    /**
     * update the liste piece
     *
     * @param game
     */
    @Override
    public void update(Model game) {

        grid.getChildren().clear();
        int i = 0;
        for (int j = 0; j <= 21; j++) {
            if (j < player.getStock().getStock().size()) {
                Piece piece = player.getStock().getStock().get(i);
                PieceGrid pg = new PieceGrid(piece, game, player);
                i++;
                grid.add(pg, j % 7, j / 7);
            } else {
                grid.add(new PieceGrid(), j % 7, j / 7);
            }
        }

        if (player == game.getCurrentPlayer()) {
            Label l = (Label) getChildren().get(0);
            l.setText(infoGame(game));
        }

        if (game.endOfTheGame()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Game over!");
            alert.setHeaderText("The game is over");

            alert.setContentText("The winner is: " + game.getWinner());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Platform.exit();
            } else {
                Platform.exit();
            }
        }
    }

    /**
     * clear the list
     *
     * @param game
     */
    public void clearList(Model game) {
        fillPiece(game);
        infoGame(game);
        player = game.getPlayers().get(idPlayer);
    }
}
