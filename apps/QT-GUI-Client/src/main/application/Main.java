package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import model.DataModel;
import view.AlertDialog;
import view.MainView;

/**
 * This class rappresent the beginning of the software (client-side)
 */

public class Main extends Application {
	public static void main(String args[]) {
		launch(args);
	}
	
	/**
	 * Starts the client
	 * @param primaryStage the firt stage
	 */
	public void start(Stage primaryStage) {
		DataModel model = new DataModel();
		try {
			new MainView(model);
		} catch(IOException e) {
			new AlertDialog(AlertType.ERROR,
					"FATAL ERROR",
					"ERROR",
					"Couldn't find the MainView fxml file! "
					);
		}
	}
}
