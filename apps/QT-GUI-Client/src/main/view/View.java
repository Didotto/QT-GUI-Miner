package view;

import java.io.IOException;

import controller.Controller;
import model.DataModel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class View {
	private static final String FXMLPATH = "../layouts/";
	
	private static final String ICONPATH = "file:src/main/resources/";
	
	private FXMLLoader loader;
	
	private Controller controller;
	
	private Scene scene;
	
	private Stage stage;
	
	private Pane root;
	
	private DataModel model;
	
	public View(DataModel model, String LayoutFileName, String IconFileName, String Title) throws IOException {
		loader = new FXMLLoader(getClass().getResource(FXMLPATH + LayoutFileName));
		root = loader.load();
		controller = loader.getController();
		this.model = model;
		
		stage = new Stage();
        scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        
        stage.setScene(scene);
        stage.setTitle(Title);
        
        stage.getIcons().add(new Image(ICONPATH + IconFileName));
        
        
	}
	
	public FXMLLoader getLoader() {
		return loader;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public DataModel getModel() {
		return model;
	}
}
