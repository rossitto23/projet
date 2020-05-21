package atlg4.ultimate.g49282;

import atlg4.ultimate.g49282.model.Game;
import atlg4.ultimate.g49282.model.GameModel;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public class MainUTT extends Application {

    private Stage primaryStage;
    private AnchorPane stack;

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("UltimateTicTacToe");
        GameModel game = new Game();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UltimateTicTacToe.class.getResource("view/UTT.fxml"));
            MyUTTController uc = new MyUTTController((Game) game);
            loader.setController(uc);

            stack = (AnchorPane) loader.load();

            Scene scene = new Scene(stack);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
