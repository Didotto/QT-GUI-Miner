package controller;

import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

import java.io.IOException;

import view.GearView;
import model.DataModel;

import java.util.Observer;
import java.util.Observable;

public class MainController extends Controller implements Observer{
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
	private Circle statusCircle;
	
	@FXML
	public void initialize() {
		loadOptions.setItems(FXCollections.observableArrayList(
				new String("Load From Database"),
				new String("Load From File")
				));
		loadOptions.getSelectionModel().select(0);
		rightPane.setDisable(true);
		
	}
	//https://www.javaworld.com/article/2077258/observer-and-observable.html
	//RIMENDER : MEGLIO PRIVATE MA CON @FXML OPPURE PUBLIC ?
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
		try {
			new GearView(this.model);
		}catch (IOException e){
			System.out.println("Errore: " + e.getMessage());
		}
	}
	
	public void update(Observable obs, Object obj) {
		DataModel m = (DataModel)obs;
		if(m.isConnected()) {
			statusCircle.setFill(Color.GREEN);
			//statusCircle.setFill(Color.BLUE);
		}else {
			statusCircle.setFill(Color.RED);
		}
	}
}
