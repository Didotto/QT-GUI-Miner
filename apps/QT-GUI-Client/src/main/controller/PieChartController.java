package controller;

import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;

import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

/**
 * This class manages some events generated by the graphic elements present in the "PieChartVIsualizzation" view
 */

public class PieChartController extends ColoredController {
	@FXML
	private PieChart pieChart;
	
	/**
	 * It allows to update the pie chart by assigning to "piece" the name of a centroid and the relative color 
	 * that can be found inside the table
	 */
	
	public void updateChart() {
		ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
		LinkedList<String> names = getNames(model.getDatabaseData().getData());
		for(int i=0; i<names.size(); i++) {
			pieData.add(new PieChart.Data(names.get(i), model.getDatabaseData().getData().get(i).size()));
		}
		pieChart.setLegendVisible(false);
		pieChart.setData(pieData);
		for(int i=0; i<names.size(); i++) {
			int id = Integer.parseInt(model.getDatabaseData().getData().get(i).get(0).get(0));
			pieData.get(i).getNode().setStyle("-fx-pie-color: #" +
			generatePastelColor(id).toString().substring(2, 8));
		}
		
	}
	
	/**
	 * Places the name of centroids sent by the server in a list of strings
	 * @param tableData the entire dataset clustered
	 * @return the name of centroids sent by the server in a list of strings
	 */
	
	private LinkedList<String> getNames(LinkedList<LinkedList<LinkedList<String>>> tableData){
		LinkedList<String> names = new LinkedList<String>();
		for(LinkedList<LinkedList<String>> cluster: tableData) {
			for(LinkedList<String> tuple: cluster) {
				if(tuple.getLast().equals("0.0")) {
					names.add(tuple.subList(0, tuple.size()-1).toString());
					break;
				}
			}
		}
		return names;
	}
}
