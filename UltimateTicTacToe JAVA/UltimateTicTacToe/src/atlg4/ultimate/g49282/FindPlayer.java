package atlg4.ultimate.g49282;

import atlg4.ultimate.g49282.exception.BusinessException;
import atlg4.ultimate.g49282.exception.DBException;
import atlg4.ultimate.g49282.exception.DTOException;
import atlg4.ultimate.g49282.model.Game;
import atlg4.ultimate.g49282.pers.PlayerDto;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This method is the controller of the FXML window to find a player.
 *
 * @author Nicolas Rossitto <49282@etu.he2b.be>
 */
public class FindPlayer {

    @FXML
    private TextField choosePlay;

    @FXML
    private Button ok;

    @FXML
    private Button home;

    private Game game;

    public FindPlayer(Game game) {
        this.game = game;
    }

    @FXML
    public void initialize() {
        home.setOnAction((event) -> {
            home();
        });

        ok.setOnAction((event) -> {
            try {
                ok();
            } catch (DBException ex) {
                Logger.getLogger(FindPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DTOException ex) {
                Logger.getLogger(FindPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BusinessException ex) {
                Logger.getLogger(FindPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void home() {
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("view/Welcome.fxml"));
            WelcomeController w = new WelcomeController(game);
            l.setController(w);

            Parent root = l.load();

            Scene scene = new Scene(root);
            Stage s = new Stage();

            s.setMinHeight(450);
            s.setMinWidth(650);
            s.setScene(scene);
            s.show();

            Stage st = (Stage) home.getScene().getWindow();
            st.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ok() throws DBException, DTOException, BusinessException {
        try {
            if (checkPlayExist(choosePlay.getText())) {
                FXMLLoader l = new FXMLLoader(getClass().getResource("view/HistoC.fxml"));
                HistoController c = new HistoController(game, choosePlay.getText());
                l.setController(c);
                Parent root = l.load();
                Scene scene = new Scene(root);
                Stage s = new Stage();
                s.setMinHeight(450);
                s.setMinWidth(650);
                s.setScene(scene);
                s.show();

                Stage st = (Stage) ok.getScene().getWindow();
                st.close();
            } else if (choosePlay.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Score");
                alert.setHeaderText("WARNING :");
                alert.setContentText("enter a pseudonym!");
                alert.showAndWait();
            } else if (!checkPlayExist(choosePlay.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Score");
                alert.setHeaderText("WARNING :");
                alert.setContentText("this player does not exist!");
                alert.showAndWait();
            }
        } catch (IOException e) {

        }
    }

    public boolean checkPlayExist(String name) throws DBException, DTOException {
        for (PlayerDto pl : game.getDatabase().getAllPlayers()) {
            if (pl.getPseudo().equals(name)) {
                return true;
            }
        }

        return false;
    }

}
