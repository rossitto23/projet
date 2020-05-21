package blokus_rossitto.viewFx;

import blokus_rossitto.model.Game;
import blokus_rossitto.model.Model;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BlokusMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Stage windows;
 //   Scene scene1, scene2;

    Scene first;
 //   VBox layoutt = new VBox(20);
    Stage primaryStage;
    

    public void startGame(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox, 1500, 1250);
         Model game = new Game();
      

        Button start = new Button("start");
        start.setOnAction(e -> windows.setScene(scene));

      

        VBox firstvbox = new VBox();

        primaryStage.setTitle("Blokus");
        primaryStage.setResizable(false);

       

        Scene first = new Scene(firstvbox, 900, 600);
        Button starts = new Button("Nouvelle partie");

        starts.setOnAction(e -> {
            try {
                startGame(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(BlokusMain.class.getName()).log(Level.SEVERE, null, ex);

            }
        });
        Index firstscene = new Index(game, primaryStage, starts);
        firstvbox.getChildren().addAll(firstscene);
        primaryStage.setScene(first);
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            startGame(primaryStage);
        } catch (Exception e) {
            Logger.getLogger(BlokusMain.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
