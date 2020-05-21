package blokus_rossitto.viewFx;

import blokus_rossitto.model.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PlayersPane extends VBox {

    private final ListPiece l1;
    private final ListPiece l2;
    private final ListPiece l3;
    private final ListPiece l4;

    public PlayersPane(Model game) {
        super();
        this.l1 = new ListPiece(0, game);
        this.l2 = new ListPiece(1, game);
        this.l3 = new ListPiece(2, game);
        this.l4 = new ListPiece(3, game);

        GridPane grid = new GridPane();

        grid.add(l1, 0, 0);
        grid.add(l2, 0, 1);
        grid.add(l3, 0, 2);
        grid.add(l4, 0, 3);

        grid.setPadding(new Insets(1));
        grid.setAlignment(Pos.TOP_LEFT);
        this.getChildren().add(grid);

    }

    public void updateList(Model game) {
        this.l1.clearList(game);
        this.l2.clearList(game);
        this.l3.clearList(game);
        this.l4.clearList(game);
    }

}
