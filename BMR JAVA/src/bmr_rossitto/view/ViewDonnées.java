package bmr_rossitto.view;

import bmr_rossitto.model.StyleVie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ViewDonnées extends GridPane {

    private final Label données = new Label("Données");
    private final Label taille = new Label("Taille (cm)");
    private final Label poids = new Label("Poids (kg)");
    private final Label age = new Label("age(années)");
    private final Label sexe = new Label("Sexe");
    private final Label styleVie = new Label("Style de vie ");

    private final RadioButton radioButtonH = new RadioButton("Homme");
    private final RadioButton radioButtonF = new RadioButton("Femme");
    private final ToggleGroup radioGroup = new ToggleGroup();

    private final TextField tailleAnswer = new TextField();
    private final TextField poidsAnswer = new TextField();
    private final TextField ageAnswer = new TextField();
    private final ChoiceBox<StyleVie> choice = new ChoiceBox<>();

    /**
     * Create the GridPane ViewDonnées. Initialise all Label
     */
    public ViewDonnées() {
        super.setAlignment(Pos.CENTER_LEFT);
        super.setPadding(new Insets(20));
        super.setHgap(10);
        super.setVgap(15);

        initDonnées();
        initTaille();
        initPoids();
        initAge();
        initSexe();
        initStyleVie();
        initRadioButton();
        addTextField();
        initChoice();

    }

    private void initDonnées() {
        données.setTextFill(Color.BLACK);
        données.setAlignment(Pos.TOP_LEFT);
        données.setUnderline(true);
        add(données, 1, 1);
    }

    private void initTaille() {
        taille.setTextFill(Color.BLACK);
        add(taille, 1, 2);
    }

    private void initPoids() {
        poids.setTextFill(Color.BLACK);
        add(poids, 1, 3);
    }

    private void initAge() {
        age.setTextFill(Color.BLACK);
        add(age, 1, 4);
    }

    private void initSexe() {
        sexe.setTextFill(Color.BLACK);
        add(sexe, 1, 5);
    }

    private void initStyleVie() {
        styleVie.setTextFill(Color.BLACK);
        add(styleVie, 1, 6);
    }

    private void initRadioButton() {
        radioButtonH.setToggleGroup(radioGroup);
        radioButtonF.setToggleGroup(radioGroup);
        add(radioButtonH, 2, 5);
        add(radioButtonF, 3, 5);
    }

    private void addTextField() {
        tailleAnswer.setPromptText("Taille en cm");
        poidsAnswer.setPromptText("Poids en kg");
        ageAnswer.setPromptText("Age en année");
        add(tailleAnswer, 2, 2);
        add(poidsAnswer, 2, 3);
        add(ageAnswer, 2, 4);

    }

    private void initChoice() {

        choice.getItems().addAll(StyleVie.values());
        choice.setValue(StyleVie.SEDENTAIRE);
        add(choice, 2, 6);
    }

    /**
     * return the value of tailleAnswer
     *
     * @return tailleAnswer
     */
    public double getTailleAnswer() {

        return Double.parseDouble(tailleAnswer.getText());
    }

    /**
     * reutnr the value of poidsAnswer
     *
     * @return poidsAnswer
     */
    public double getPoidsAnswer() {
        return Double.parseDouble(poidsAnswer.getText());
    }

    /**
     * return the value of ageAnswer
     *
     * @return ageAnswer
     */
    public int getAgeAnswer() {
        return Integer.parseInt(ageAnswer.getText());
    }

    /**
     * return the value of choice
     *
     * @return choice.getValue().getValue()
     */
    public double getValue() {
        return choice.getValue().getValue();
    }

    /**
     * return true if is a man
     *
     * @return true or false
     */
    public boolean isMan() {
        return radioButtonH.isSelected();
    }

    /**
     * return true if is a woman
     *
     * @return true or false
     */
    public boolean isWoman() {
        return radioButtonF.isSelected();
    }

    /**
     * Clear all Data
     */
    public void clear() {
        tailleAnswer.clear();
        poidsAnswer.clear();
        ageAnswer.clear();
        radioButtonF.setSelected(false);
        radioButtonH.setSelected(false);
        choice.setDisable(false);
    }

}
