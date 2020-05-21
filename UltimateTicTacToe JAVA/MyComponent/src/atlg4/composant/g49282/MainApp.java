package atlg4.composant.g49282;

import atlg4.composant.g49282.view.MyTicTacToe;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Nicolas Rossitto 49282
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private StackPane rootLayout;

    private MyTicTacToe ttt;
    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TicTacToeUltimate");
        this.primaryStage.setResizable(false);
        ttt = new MyTicTacToe();
        Scene scene = new Scene(ttt);
        primaryStage.setScene(scene);
        primaryStage.show();
        addEvents();
        //initTicTacToeBoard();

    }

    /**
     * initialize the tic tac toe and load the SceneBuilder
     */
    public void initTicTacToeBoard() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TicTacToe.fxml"));
            rootLayout = (StackPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addEvents(){
        for(int i =0;i<9;i++){
            ttt.addEvent(i,new MyEvent(i,ttt));
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

