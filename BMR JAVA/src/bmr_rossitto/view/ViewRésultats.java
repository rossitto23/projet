package bmr_rossitto.view;

import bmr_rossitto.model.Bmr;
import bmr_rossitto.util.Observable;
import bmr_rossitto.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ViewRésultats extends GridPane implements Observer {

    private final Label résultats = new Label("Résultat");
    private final Label bmr = new Label("BMR");
    private final Label calories = new Label("Calories");

    private final TextField bmrAnswer = new TextField();
    private final TextField caloriesAnswer = new TextField();

    private final Bmr bmr1;

    /**
     * Create the GridPane ViewRésultats. Initialise all Label
     *
     * @param obs is the observed
     */
    public ViewRésultats(Observable obs) {

        super.setAlignment(Pos.TOP_RIGHT);
        super.setPadding(new Insets(20));
        super.setHgap(10);
        super.setVgap(15);

        if (obs == null) {
            throw new IllegalArgumentException("error");
        }

        bmr1 = (Bmr) obs;
        bmr1.registerObserver(this);

        initRésultats();
        initBmr();
        addTextField();
        initCalories();
    }

    private void initRésultats() {
        résultats.setTextFill(Color.BLACK);
        résultats.setAlignment(Pos.TOP_LEFT);
        résultats.setUnderline(true);
        add(résultats, 1, 1);
    }

    private void initBmr() {
        bmr.setTextFill(Color.BLACK);
        add(bmr, 1, 2);
    }

    private void initCalories() {
        calories.setTextFill(Color.BLACK);
        add(calories, 1, 3);
    }

    private void addTextField() {
        bmrAnswer.setPromptText("Résultats du BMR");
        caloriesAnswer.setPromptText("Dépense en calories");
        add(bmrAnswer, 2, 2);
        add(caloriesAnswer, 2, 3);
    }

    /**
     * changes the value of bmr
     *
     * @param nb
     */
    public void setBmr(double nb) {
        bmrAnswer.setText(Double.toString(nb));
    }

    /**
     * changes the value of calories
     *
     * @param nb
     */
    public void setCalories(double nb) {
        caloriesAnswer.setText(Double.toString(nb));

    }

    /**
     * if b is false, the message "failed!" will be marked in bmrAnswer
     *
     * @param b
     */
    public void setTextBmrFailed(boolean b) {
        bmrAnswer.setDisable(false);
        if (b == false) {
            this.bmrAnswer.setText("Failed!");
            this.bmrAnswer.setStyle("-fx-text-inner-color: red;");
        } else {
            this.bmrAnswer.setStyle("-fx-text-inner-color: black;");
        }
    }

    public void reset() {
        this.bmrAnswer.setStyle("-fx-text-inner-color: black;");
        this.caloriesAnswer.setStyle("-fx-text-inner-color: black;");
    }

    /**
     * if b is false, the message "failed!" will be marked in caloriesAnswer
     *
     * @param b
     */
    public void setTextCaloriesFailed(boolean b) {
        caloriesAnswer.setDisable(false);
        if (b == false) {
            this.caloriesAnswer.setText("Failed!");
            this.caloriesAnswer.setStyle("-fx-text-inner-color: red;");
        } else {
            this.caloriesAnswer.setStyle("-fx-text-inner-color: black;");
        }
    }

    /**
     * clear bmrAnswer and caloriesAnswer
     */
    public void clear() {
        bmrAnswer.clear();
        caloriesAnswer.clear();
    }

    /**
     * update the bmr and the calories
     */
    @Override
    public void update() {
        setBmr(bmr1.getBmr());
        setCalories(bmr1.getCalories());
    }

}
