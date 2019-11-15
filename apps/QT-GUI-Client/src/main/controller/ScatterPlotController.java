package controller;

import javafx.scene.control.ComboBox;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;

public class ScatterPlotController extends Controller {
	@FXML
	private ComboBox <String> comboBox1;
	@FXML
	private ComboBox <String> comboBox2;
	@FXML
	//ho messo string string ma non so
	private ScatterChart<String,String> scatterPlot;
}
