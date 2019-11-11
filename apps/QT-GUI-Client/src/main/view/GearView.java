package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import controller.GearController;
import model.DataModel;

public class GearView {
	private static final String FXMLPATH = "../view/connectionOptions.fxml";
	
	private static final String ICONPATH = "file:src/main/view/rotella.png";
	
	private static final String TITLE = "SETTINGS";
	
	private FXMLLoader loader;
	
	private GearController controller;
	
	private Stage stage;
	
	private Scene scene;
	
	private AnchorPane root;
	
	public GearView(DataModel model) throws IOException {
		//probabilmente va definito in una nuova classe View
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLPATH));
		root = (AnchorPane)loader.load();
		GearController controller = loader.getController();
		
		
		stage = new Stage();
		scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
		stage.setScene(scene);
		
		stage.setResizable(false);
        stage.setTitle(TITLE);
        
        stage.getIcons().add(new Image(ICONPATH));
        controller.init(model, stage);
		stage.show();
	}
	
}
