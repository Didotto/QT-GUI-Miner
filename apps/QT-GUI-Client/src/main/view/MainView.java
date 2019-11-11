package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import model.DataModel;
import controller.MainController;

import java.util.Observer;
import java.util.Observable;

import javafx.application.Platform;

public class MainView extends Application{
	
	private FXMLLoader loader;
	
	private MainController controller;
	
	private Scene scene;
	
	private Stage stage;
	
	public static void main(String args[]) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		loader = new FXMLLoader(getClass().getResource("prova.fxml"));
		Parent root = loader.load();
		
		controller = loader.getController();
		DataModel model = new DataModel();
		model.addObserver(controller);
		
        scene = new Scene(root, 550, 500);
        stage = primaryStage;
        primaryStage.setTitle("QT-Miner JavaFX");
        
        //Disable Split Pane divider
        SplitPane splitPane = (SplitPane)scene.lookup("#splitPane");
        splitPane.lookupAll(".split-pane-divider").stream()
    		.forEach(div ->  div.setMouseTransparent(true) );
        //REMINDER : EDIT PATH OF THE IMAGE
        primaryStage.getIcons().add(new Image("file:src/main/resources/mining.png"));
		
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);
        
        primaryStage.setOnHidden(e -> Platform.exit());
        
        primaryStage.setScene(scene);
        
        controller.init(model, stage);
        primaryStage.show(); 
	}

}
