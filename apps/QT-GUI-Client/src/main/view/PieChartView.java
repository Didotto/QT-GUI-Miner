package view;

import java.io.IOException;
import java.util.LinkedList;

import model.DataModel;
import controller.PieChartController;
public class PieChartView extends View {
	private static final String TITLE = "CHART VIEW";
	
	private static final String ICON = "mining.png";
	
	private static final String LAYOUT = "pieChart.fxml";
	
	public PieChartView(DataModel model) throws IOException {
		super(model, LAYOUT, ICON, TITLE);
		
		getController().init(model, getStage());
		((PieChartController)getController()).updateChart();
		getStage().show();
	}
}
