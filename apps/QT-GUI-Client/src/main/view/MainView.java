package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainView extends Application {
	
	public static void main(String args[]) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("prova.fxml")); 
        Scene scene = new Scene(root, 550, 500);
        primaryStage.setTitle("QT-Miner JavaFX");
        
        //Disable Split Pane divider
        SplitPane splitPane = (SplitPane)scene.lookup("#splitPane");
        splitPane.lookupAll(".split-pane-divider").stream()
    		.forEach(div ->  div.setMouseTransparent(true) );
        //REMINDER : EDIT PATH OF THE IMAGE
        primaryStage.getIcons().add(new Image("file:src/main/view/mining.png"));
		
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);
        
        primaryStage.setScene(scene);
        primaryStage.show(); 
	}

}
