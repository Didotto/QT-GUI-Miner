package view;

import java.io.IOException;
import java.net.SocketException;

import controller.DataVisualizationController;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import model.DataModel;
import controller.ServerException;

/**
 * This class models the view of the stage
 * that will appear to the client when
 * the results are shown
 */

public class DataVisualizationView extends View {
	/**
	 * Display the data visualization stage to the client and hide
	 * or show some elements based on the user's decision
	 *
	 * @param model contains the information needed and stored for the client
	 * @throws IOException if an I/O error occurs when using the stage
	 */
	
	public DataVisualizationView(DataModel model) throws IOException {
        super(model, "dataVisualization.fxml", "mining.png", "DATA VISUALIZATION");
        
        getStage().initModality(Modality.APPLICATION_MODAL);
        getController().init(model, getStage());
        
        AnchorPane root = (AnchorPane)getStage().getScene().getRoot();
        getStage().setMinWidth(root.getPrefWidth());
        getStage().setMinHeight(root.getPrefHeight());
        if (!model.isLoadDB()) {
        	root.getChildren().get(1).setVisible(false);
        	root.getChildren().get(2).setVisible(false);
        }else
        	root.getChildren().get(4).setVisible(false);
        try {
        	((DataVisualizationController)getController()).updateTable(model.isLoadDB());
        	getStage().show();
        }catch (SocketException e){
        	new AlertDialog(AlertType.ERROR,
					"ERROR",
					"COMMUNICATION ERROR WITH SERVER",
					e.getMessage()
					);
        }catch (IOException | ClassNotFoundException e) {
        	new AlertDialog(AlertType.ERROR,
					"ERROR",
					"FATAL ERROR",
					e.getMessage()
					);
        }catch (ServerException e) {
        	new AlertDialog(AlertType.ERROR,
					"ERROR",
					"USER INPUT ERROR",
					e.getMessage()
					);
        }
		
		
	}
}
