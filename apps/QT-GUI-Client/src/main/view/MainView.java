package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import model.DataModel;
import controller.MainController;


import javafx.application.Platform;

public class MainView extends Application{
	private static final String FXMLPATH = "../layouts/main.fxml";
	
	private static final String ICONPATH = "file:src/main/resources/mining.png";
	
	private static final String TITLE = "QT-Miner JavaFX";
	
	private FXMLLoader loader;
	
	private MainController controller;
	
	private Scene scene;
	
	private Stage stage;
	
	private AnchorPane root;
	
	public static void main(String args[]) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		loader = new FXMLLoader(getClass().getResource(FXMLPATH));
		root = (AnchorPane)loader.load();
		controller = loader.getController();
		
		DataModel model = new DataModel();
		model.addObserver(controller);
		
		stage = new Stage();
        scene = new Scene(root, 550, 500);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        
        //Disable Split Pane divider
        SplitPane splitPane = (SplitPane)scene.lookup("#splitPane");
        splitPane.lookupAll(".split-pane-divider").stream()
    		.forEach(div ->  div.setMouseTransparent(true) );

        stage.getIcons().add(new Image(ICONPATH));
		
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        
        stage.setOnHidden(e -> Platform.exit());
       
        controller.init(model, stage);
        stage.show(); 
	}

}
