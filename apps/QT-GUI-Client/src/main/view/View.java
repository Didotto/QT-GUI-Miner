package view;

import java.io.IOException;

import controller.Controller;
import model.DataModel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;


/**
 * This class models a generic view
 * and defines his generic attributes and operations
 */

public class View {
	private static final String FXMLPATH = "/layouts/";
	
	private static final String ICONPATH = "/resources/";
	
	private FXMLLoader loader;
	
	private Controller controller;
	
	private Scene scene;
	
	private Stage stage;
	
	private Pane root;
	
	private DataModel model;
	
	/**
	 * Describe the attributes and operations shared by
	 * a generic view that has to be loaded
	 *
	 * @param model contains the information needed and stored for the client
	 * @param LayoutFileName the FXML's file name associated to a specific view
	 * @param IconFileName the icon's file name associated to a specific stage
	 * @param Title the title of the stage that will be created
	 * @throws IOException if an I/O error occurs when using the stage
	 */
	
	public View(DataModel model, String layoutFileName, String iconFileName, String title) throws IOException {
		loader = new FXMLLoader(getClass().getResource(FXMLPATH + layoutFileName));
		root = loader.load();
		controller = loader.getController();
		this.model = model;
		
		stage = new Stage();
        scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        
        stage.setScene(scene);
        stage.setTitle(title);
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream(ICONPATH + iconFileName)));
        
        
	}
	
	/**
	 * Return the loader used for FXML files
	 * @return the loader used for FXML files
	 */
	
	public FXMLLoader getLoader() {
		return loader;
	}
	
	/**
	 * Return the controller associated with a view
	 * @return the controller associated with a view
	 */
	
	public Controller getController() {
		return controller;
	}
	
	/**
	 * Return the stage associated with a view
	 * @return the stage associated with a view
	 */	
	
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * Return the model used to manage the client's data
	 * @return the model used to manage the client's data
	 */
	
	public DataModel getModel() {
		return model;
	}
}
