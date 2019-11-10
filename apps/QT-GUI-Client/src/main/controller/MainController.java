package controller;

import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class MainController{
	@FXML
	private AnchorPane mainAnchorPane;
	
	@FXML
	private ComboBox<String> loadOptions;
	
	@FXML
	private AnchorPane leftPane;
	
	@FXML
	private AnchorPane rightPane;
	
	@FXML
	private ImageView gearIcon;
	
	@FXML
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
	
	public void gearIconCliecked(MouseEvent event) {
		AnchorPane root;
		try {
			//probabilmente va definito in una nuova classe View
			root = FXMLLoader.load(getClass().getResource("../view/connectionOptions.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root, root.getPrefWidth(), root.getPrefHeight()));
			stage.show();
		} catch(IOException e) {
			System.out.println("Errore " + e.getMessage());
		} catch(Exception e ) {
			System.out.println("Errore " + e.getMessage());
		}
	}
}
