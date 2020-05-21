package blokus_rossitto.viewFx;

import blokus_rossitto.model.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class History extends GridPane {

    public History(Model game) {
        super.setAlignment(Pos.CENTER);
        super.setPadding(new Insets(20));
        super.setHgap(10);
        super.setVgap(15);

        
        TextField joueur1 = new TextField(lastAction(game, 0, "joueur1"));
        TextField joueur2 = new TextField(lastAction(game, 1, "joueur2"));
        TextField joueur3 = new TextField(lastAction(game, 2, "joueur3"));
        TextField joueur4 = new TextField(lastAction(game, 3, "joueur4"));

        this.add(joueur1, 0, 0);
        this.add(joueur2, 0, 1);
        this.add(joueur3, 0, 2);
        this.add(joueur4, 0, 3);
       
    }

    public String lastAction(Model game, int player, String name) {
        String st = new String();
        st += name + ": ";
        for (int j = 0; j < game.getPlayers().get(player).getStockPieceRemove().size(); j++) {
            st += "Piece ";
            st += game.getPlayers().get(player).getStockPieceRemove().get(j).getIdPiece();
            st += ("\n");
        }
        return st;
    }
}
