package atlg4.ultimate.g49282;

import atlg4.ultimate.g49282.StartGameController;
import atlg4.ultimate.g49282.model.Game;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public class WelcomeController {

    @FXML
    private Button newGame;

    @FXML
    private Button histo;

    @FXML
    private Button log_in;

    private Game game;

    public WelcomeController(Game game) {
        this.game = game;
    }

    @FXML
    public void initialize() {
        newGame.setOnAction(((event) -> {
            newGame();
        }));

        histo.setOnAction((event) -> {
            histo();
        });

        log_in.setOnAction((event) -> {
            log_in();
        });
    }

    private void newGame() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/StartGame.fxml"));
            StartGameController start = new StartGameController(game);
            loader.setController(start);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage s = new Stage();

            s.setMinHeight(450);
            s.setMinWidth(650);
            s.setScene(scene);
            s.show();

            Stage st = (Stage) newGame.getScene().getWindow();
            st.close();
        } catch (IOException e) {

        }
    }

    private void histo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Find.fxml"));
            FindPlayer h = new FindPlayer(game);
            loader.setController(h);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage s = new Stage();

            s.setMinHeight(450);
            s.setMinWidth(650);
            s.setScene(scene);
            s.show();

            Stage st = (Stage) histo.getScene().getWindow();
            st.close();

        } catch (IOException e) {

        }

    }

    private void log_in() {

        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("view/Log_in.fxml"));
            LogInController li = new LogInController(game);
            l.setController(li);

            Parent root = l.load();
            Scene scene = new Scene(root);
            Stage s = new Stage();

            s.setMinHeight(450);
            s.setMinWidth(650);

            s.setScene(scene);
            s.show();

            Stage st = (Stage) histo.getScene().getWindow();
            st.close();
        } catch (IOException e) {

        }

    }
}
