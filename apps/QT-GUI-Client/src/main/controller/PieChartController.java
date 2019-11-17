package controller;

import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;

import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

public class PieChartController extends Controller {
	@FXML
	private PieChart pieChart;
	
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
			Color background = Color.hsb(
					(id * 67.0d) % 360.0d,
					(0.3d + id*0.01) %1.0d,
					0.8d);
			pieData.get(i).getNode().setStyle("-fx-pie-color: #" + background.toString().substring(2, 8));
		}
		
	}
	
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
