package bmr_rossitto.model;

import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Graph extends LineChart<Number, Number> {

    private final XYChart.Series menData;
    private final XYChart.Series womenData;

    /**
     * construct a graph with these two axes
     *
     * @param xAxis the abscissa of the point
     * @param yAxis the ordinate of the point
     */
    public Graph(NumberAxis xAxis, NumberAxis yAxis) {

        super(xAxis, yAxis);

        super.setPadding(new Insets(20));
        super.setHeight(10);
        super.setWidth(15);
        super.setTitle("BMR Index Vs Weight");

        menData = new XYChart.Series();
        menData.setName("Men Data");

        womenData = new XYChart.Series();
        womenData.setName("Women Data");

        this.getData().addAll(this.menData, this.womenData);
    }

    /**
     * add a point for men on the graph
     *
     * @param x the abscissa of the point
     * @param y the ordinate of the point
     */
    public void addMen(double x, double y) {
        menData.getData().add(new XYChart.Data<>(x, y));
    }

    /**
     * add a point for women on the graph
     *
     * @param x the abscissa of the point
     * @param y the ordinate of the point
     */
    public void addWomen(double x, double y) {
        womenData.getData().add(new XYChart.Data<>(x, y));
    }

}
