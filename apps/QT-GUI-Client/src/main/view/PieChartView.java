package view;

import java.io.IOException;
import java.util.LinkedList;

import model.DataModel;
import controller.PieChartController;

/**
 * This class models the view of the stage used
 * to visualize the results obtained through
 * the clustering process using a pie-chart.
 * (Useful to determine the clusters distribution)
 */

public class PieChartView extends View {
	private static final String TITLE = "CHART VIEW";
	
	private static final String ICON = "mining.png";
	
	private static final String LAYOUT = "pieChart.fxml";
	
	/**
	 * Display the stage used to visualize the results
	 * using a pie-chart obtained by the user's input
	 *
	 * @param model contains the information needed and stored for the client
	 * @throws IOException if an I/O error occurs when using the stage
	 */
	
	public PieChartView(DataModel model) throws IOException {
		super(model, LAYOUT, ICON, TITLE);
		
		getController().init(model, getStage());
		((PieChartController)getController()).updateChart();
		getStage().show();
	}
}
