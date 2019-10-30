package controller;

import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

public class MainController{
	@FXML
	private AnchorPane mainAnchorPane;
	
	@FXML
	private ComboBox<String> loadOptions;
	
	@FXML
	private AnchorPane leftPane;
	
	@FXML
	private AnchorPane rightPane;
	
	public void initialize() {
		loadOptions.setItems(FXCollections.observableArrayList(
				new String("Load From Database"),
				new String("Load From File")
				));
		loadOptions.getSelectionModel().select(0);
		rightPane.setDisable(true);
	}
	
	public void changeLoadOption(ActionEvent event) {
		if(loadOptions.getSelectionModel().getSelectedIndex() == 0) {
			//TO-DO ask the server for table names
			rightPane.setDisable(true);
			leftPane.setDisable(false);
		} else {
			//TO-DO ask the server for file names
			rightPane.setDisable(false);
			leftPane.setDisable(true);
		}
	}
}
