package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import controller.GearController;
import model.DataModel;

public class GearView {
	private static final String FXMLPATH = "../layouts/connectionOptions.fxml";
	
	private static final String ICONPATH = "file:src/main/resources/rotella.png";
	
	private static final String TITLE = "SETTINGS";
	
	private FXMLLoader loader;
	
	private GearController controller;
	
	private Stage stage;
	
	private Scene scene;
	
	private AnchorPane root;
	
	public GearView(DataModel model) throws IOException {
		loader = new FXMLLoader(getClass().getResource(FXMLPATH));
		root = (AnchorPane)loader.load();
		controller = loader.getController();
		
		
		stage = new Stage();
		scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
		stage.setScene(scene);
		
		stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(ICONPATH));
        controller.init(model, stage);
        
		stage.show();
	}
	
}
