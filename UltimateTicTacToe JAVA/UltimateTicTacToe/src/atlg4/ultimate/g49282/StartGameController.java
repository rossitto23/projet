package atlg4.ultimate.g49282;

import atlg4.ultimate.g49282.exception.BusinessException;
import atlg4.ultimate.g49282.exception.DTOException;
import atlg4.ultimate.g49282.model.Game;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public class StartGameController {

    @FXML
    private ComboBox<String> player1;

    @FXML
    private ComboBox<String> player2;

    @FXML
    private Button back;

    @FXML
    private Button log_in;

    @FXML
    private Button startGame;

    private Game game;

    public StartGameController(Game game) {
        this.game = game;
    }

    @FXML
    public void initialize() {
        player1.setItems(game.getDatabase().getAllRegistredPlayer());
        player1.setEditable(true);
        player2.setItems(game.getDatabase().getAllRegistredPlayer());
        player2.setEditable(true);

        back.setOnAction((event) -> {
            back();
        });

        startGame.setOnAction((event) -> {
            try {
                play();
            } catch (BusinessException e) {

            } catch (DTOException e) {

            }
        });

        log_in.setOnAction((event) -> {
            log_in();
        });
    }

    private void back() {
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("view/Welcome.fxml"));
            WelcomeController w = new WelcomeController(game);
            l.setController(w);
            Parent root = l.load();
            Scene scene = new Scene(root);
            Stage s = new Stage();
            s.setMinHeight(450);
            s.setMaxWidth(650);
            s.setScene(scene);
            s.show();

            Stage ss = (Stage) back.getScene().getWindow();
            ss.close();

        } catch (IOException e) {

        }
    }

    private void play() throws BusinessException, DTOException {

        if (player1.getSelectionModel().getSelectedItem() != null && player2.getSelectionModel().getSelectedItem() != null
                && !player1.getSelectionModel().getSelectedItem().equals(player2.getSelectionModel().getSelectedItem())) {

            try {

                FXMLLoader l = new FXMLLoader(getClass().getResource("view/UTT.fxml"));
                Game game = new Game();
                game.addPlayer(player1.getValue());
                game.addPlayer(player2.getValue());
                MyUTTController w = new MyUTTController(game);
                l.setController(w);
                Parent root = l.load();
                Scene scene = new Scene(root);
                Stage s = new Stage();

                s.setScene(scene);
                s.show();

                Stage ss = (Stage) back.getScene().getWindow();
                ss.close();
            } catch (IOException ex) {

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game is not possible");
            alert.setHeaderText("WARNING :");
            alert.setContentText("Please select 2 different player");
            alert.showAndWait();
        }
    }

    private void log_in() {
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("view/Log_in.fxml"));
            LogInController log = new LogInController(game);
            l.setController(log);

            Parent root = l.load();
            Scene scene = new Scene(root);
            Stage s = new Stage();

            s.setMinHeight(450);
            s.setMinWidth(650);
            s.setScene(scene);
            s.show();

            Stage st = (Stage) log_in.getScene().getWindow();
            st.close();

        } catch (IOException e) {

        }

    }

    public ComboBox<String> getPlayer1() {
        return player1;
    }

    public ComboBox<String> getPlayer2() {
        return player2;
    }

}
