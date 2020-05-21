package atlg4.ultimate.g49282;

import atlg4.ultimate.g49282.exception.BusinessException;
import atlg4.ultimate.g49282.exception.DBException;
import atlg4.ultimate.g49282.exception.DTOException;
import atlg4.ultimate.g49282.model.Game;
import atlg4.ultimate.g49282.pers.PlayerDto;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public class HistoController {

    @FXML
    private Label namePlayer = new Label("");
    @FXML
    private Label win = new Label("");
    @FXML
    private Label ex_aequo = new Label("");
    @FXML
    private Label defeat = new Label("");
    @FXML
    private Button back = new Button();

    private String pseudonym;
    private Game game;
    private String name;

    public HistoController(Game game, String name) throws BusinessException, DTOException, DBException {
        this.name = name;
        this.game = game;

    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() throws BusinessException, DTOException, DBException {
        namePlayer.setText(getPlayer().getPseudo());
        win.setText(getPlayer().getNbWin() + "");
        ex_aequo.setText(getPlayer().getNbExAequo() + "");
        defeat.setText(getPlayer().getNbDefeat() + "");

        back.setOnAction((event) -> {
            back();
        });

    }

    public void back() {
        back.setOnAction((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Welcome.fxml"));
                WelcomeController c = new WelcomeController(game);
                loader.setController(c);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setMinHeight(450);
                stage.setMinWidth(650);
                stage.setScene(scene);
                stage.show();
                Stage st = (Stage) back.getScene().getWindow();
                st.close();
            } catch (IOException ex) {
            }
        });
    }

    private PlayerDto getPlayer() throws BusinessException, DTOException, DBException {
        for (PlayerDto player : game.getDatabase().getAllPlayers()) {
            if (player.getPseudo().equals(name)) {
                return player;
            }
        }
        return null;
    }
}
