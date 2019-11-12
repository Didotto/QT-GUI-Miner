package view;

import java.io.IOException;

import controller.DataVisualizationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DataModel;

public class DataVisualizationView {
private static final String FXMLPATH = "../view/dataVisualization.fxml";
	
	private static final String ICONPATH = "file:src/main/resources/mining.png";
	
	private static final String TITLE = "DATA VISUALIZATION";
	
	private FXMLLoader loader;
	
	private DataVisualizationController controller;
	
	private Stage stage;
	
	private Scene scene;
	
	private AnchorPane root;
	
	public DataVisualizationView(DataModel model, boolean isLoadDB) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLPATH));
		root = (AnchorPane)loader.load();
		DataVisualizationController controller = loader.getController();
		
		
		stage = new Stage();
		scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
		stage.setScene(scene);
		
        stage.setTitle(TITLE);
        
        stage.getIcons().add(new Image(ICONPATH));
        controller.init(model, stage);
        controller.updateTable(isLoadDB);
		stage.show();
	}
}
