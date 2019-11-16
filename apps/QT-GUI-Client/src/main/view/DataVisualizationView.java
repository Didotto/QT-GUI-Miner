package view;

import java.io.IOException;

import controller.DataVisualizationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DataModel;
import controller.ServerException;

public class DataVisualizationView {
private static final String FXMLPATH = "../layouts/dataVisualization.fxml";
	
	private static final String ICONPATH = "file:src/main/resources/mining.png";
	
	private static final String TITLE = "DATA VISUALIZATION";
	
	private FXMLLoader loader;
	
	private DataVisualizationController controller;
	
	private Stage stage;
	
	private Scene scene;
	
	private AnchorPane root;
	
	public DataVisualizationView(DataModel model, boolean isLoadDB) throws IOException {
		loader = new FXMLLoader(getClass().getResource(FXMLPATH));
		root = (AnchorPane)loader.load();
		controller = loader.getController();
		
		
		stage = new Stage();
		scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
		stage.setScene(scene);
		
        stage.setTitle(TITLE);
        
        stage.getIcons().add(new Image(ICONPATH));
        
        stage.initModality(Modality.APPLICATION_MODAL);
        
        controller.init(model, stage);
        
        if (!isLoadDB) {
        	root.getChildren().get(1).setVisible(false);
        }
        try {
        	controller.updateTable(isLoadDB);
        	stage.show();
        }catch (ServerException e) {
        	new AlertDialog(AlertType.ERROR,
					"ERROR",
					"ERROR",
					"File doesn't Exist!"
					);
        }
		
		
	}
}
