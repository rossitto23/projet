package bmr_rossitto.controller;

import bmr_rossitto.model.Bmr;
import bmr_rossitto.model.Graph;
import bmr_rossitto.model.TabPaneBMR;
import bmr_rossitto.view.AlertMessage;
import bmr_rossitto.view.ViewDonnées;
import bmr_rossitto.view.ViewRésultats;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BmrController extends Application {

    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 400;

    /**
     * launch the start method
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    private final Bmr b = new Bmr();

    private final MenuBar menu = new MenuBar();
    private final HBox hBox = new HBox(10);   // Create new HBox
    private final VBox vBox = new VBox(10);   // Create new Vbox
    private final HBox hBox2 = new HBox(10);
    private final VBox vBox2 = new VBox(10);
    private final TabPaneBMR tabPane = new TabPaneBMR(b);
    private final Button btn = new Button("Calcul du BMR"); //create new Button
    private final Button clear = new Button("Clear");       //Create new Button
    private final AlertMessage alert = new AlertMessage(AlertType.ERROR, "Valeurs éronnées");

    XYChart.Series series = new XYChart.Series();

    /**
     * Create rootDonnées of type ViewDonnées
     */
    private final ViewDonnées rootDonnées = new ViewDonnées();

    /**
     * Create rootRésultats of type ViewRésultats
     */
    private final ViewRésultats rootRésultats = new ViewRésultats(b);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Calcul BMR: ");
        primaryStage.setMinWidth(DEFAULT_WIDTH);
        primaryStage.setMinHeight(DEFAULT_HEIGHT);
        primaryStage.setResizable(false);

        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));

        hBox.setAlignment(Pos.CENTER_LEFT);

        Menu exit = new Menu("exit");
        MenuItem exitItem = new MenuItem("Exit");
        exit.getItems().add(exitItem);
        menu.getMenus().add(exit);
        exit.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        hBox.getChildren().addAll(rootDonnées, rootRésultats);
        vBox.getChildren().addAll(hBox, btn, clear);
        hBox2.getChildren().addAll(vBox, tabPane);
        vBox2.getChildren().addAll(menu, hBox2);

        hBox2.setAlignment(Pos.TOP_CENTER);
        vBox2.setAlignment(Pos.TOP_CENTER);

        rootDonnées.setAlignment(Pos.TOP_LEFT);
        rootRésultats.setAlignment(Pos.TOP_RIGHT);
        btn.setAlignment(Pos.BOTTOM_CENTER);
        btn.setMaxWidth(DEFAULT_WIDTH);
        clear.setAlignment(Pos.BOTTOM_CENTER);
        clear.setMaxWidth(DEFAULT_WIDTH);

        Clear(clear);
        CalculBmr(btn);

        Scene scene = new Scene(vBox2, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void calculBoutton() {

        rootRésultats.reset();

        b.setActivity(rootDonnées.getValue());
        b.setAge(rootDonnées.getAgeAnswer());
        b.setPoids(rootDonnées.getPoidsAnswer());
        b.setTaille(rootDonnées.getTailleAnswer());
        b.setMan(rootDonnées.isMan());

        b.calcul();
        rootRésultats.setBmr(b.getBmr());
        rootRésultats.setCalories(b.getCalories());
        series.getData().add(new TabPaneBMR(b));

    }

    /**
     * gives all TextField values ​​of rootDonnées if the date is not good,
     * error
     *
     * @param calculBmr
     */
    private void CalculBmr(Button calculBmr) {
        calculBmr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    calculBoutton();
                } catch (NumberFormatException e) {
                    rootRésultats.setTextBmrFailed(false);
                    rootRésultats.setTextCaloriesFailed(false);
                    Optional<ButtonType> result = alert.showAndWait();
                } catch (IllegalArgumentException e) {
                    rootRésultats.setTextBmrFailed(false);
                    rootRésultats.setTextCaloriesFailed(false);
                    Optional<ButtonType> result = alert.showAndWait();
                    // if(result.isPresent() && result.get() == ButtonType.OK){
                    //  formatSystem();
                    // }

                }
            }
        }
        );

    }

    private void Clear(Button b) {
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rootDonnées.clear();
                rootRésultats.clear();
            }
        });
    }

}
