package bmr_rossitto.model;

import bmr_rossitto.util.Observable;
import bmr_rossitto.util.Observer;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabPaneBMR extends TabPane implements Observer {

    private Graph line1;
    private Graph line2;
    private Graph line3;
    private final Bmr bmr;

    /**
     * create the three graphs
     *
     * @param obs is the observed
     */
    public TabPaneBMR(Observable obs) {
        super();

        if (obs == null) {
            throw new IllegalArgumentException("Error");
        }

        bmr = (Bmr) obs;
        bmr.registerObserver(this);

        initLine1();
        initLine2();
        initLine3();
    }

    /**
     * create and add of the first graph to the tabpane
     */
    private void initLine1() {
        Tab tab1 = new Tab("Weight(kg) vs BMR");
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Weight(kg)");
        yAxis.setLabel("BMR");
        tab1.setClosable(true);

        line1 = new Graph(xAxis, yAxis);

        tab1.setContent(line1);
        super.getTabs().add(tab1);
    }

    /**
     * create and add of the second graph to the tabpane
     */
    private void initLine2() {
        Tab tab2 = new Tab("Weight(kg) vs Calories");
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Weight(kg)");
        yAxis.setLabel("Calories");
        tab2.setClosable(true);

        line2 = new Graph(xAxis, yAxis);

        tab2.setContent(line2);
        super.getTabs().add(tab2);
    }

    /**
     * create and add of the third graph to the tabpane
     */
    private void initLine3() {
        Tab tab3 = new Tab("Height(cm) vs BMR");
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Height(cm)");
        yAxis.setLabel("BMR");
        tab3.setClosable(true);

        line3 = new Graph(xAxis, yAxis);

        tab3.setContent(line3);
        super.getTabs().add(tab3);
    }

    /**
     * update the graphics
     */
    @Override
    public void update() {
        if (bmr.isMan()) {
            this.line1.addMen(bmr.getPoids(), bmr.getBmr());
            this.line2.addMen(bmr.getPoids(), bmr.getCalories());
            this.line3.addMen(bmr.getTaille(), bmr.getBmr());
        } else {
            this.line1.addWomen(bmr.getPoids(), bmr.getBmr());
            this.line2.addWomen(bmr.getPoids(), bmr.getCalories());
            this.line3.addWomen(bmr.getTaille(), bmr.getBmr());
        }
    }

}
