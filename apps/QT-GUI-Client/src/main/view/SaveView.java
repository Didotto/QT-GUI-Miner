package view;

import java.io.IOException;

import controller.SaveController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DataModel;

public class SaveView {
	private static final String FXMLPATH = "../view/saveOptions.fxml";
	
	private static final String ICONPATH = "file:src/main/resources/saveIcon.png";
	
	private static final String TITLE = "SAVE OPTIONS";
	
	private FXMLLoader loader;
	
	private SaveController controller;
	
	private Stage stage;
	
	private Scene scene;
	
	private AnchorPane root;
	
	public SaveView(DataModel model) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLPATH));
		root = (AnchorPane)loader.load();
		SaveController controller = loader.getController();
		
		
		stage = new Stage();
		scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
		stage.setScene(scene);
		
        stage.setTitle(TITLE);
        
        stage.getIcons().add(new Image(ICONPATH));
        
        stage.initModality(Modality.APPLICATION_MODAL);
        /*
        controller.init(model, stage);
        controller.updateTable(isLoadDB);
        //If i load from file ... i cain't save
        if (!isLoadDB) {
        	root.getChildren().get(1).setVisible(false);
        }
		stage.show();
		*/
	}
}
