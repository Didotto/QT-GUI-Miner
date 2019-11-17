package view;

import java.io.IOException;
import java.net.SocketException;

import controller.DataVisualizationController;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import model.DataModel;
import controller.ServerException;

public class DataVisualizationView extends View {
	private static final String LAYOUT = "dataVisualization.fxml";
	
	private static final String ICON = "mining.png";
	
	private static final String TITLE = "DATA VISUALIZATION";
	
	public DataVisualizationView(DataModel model) throws IOException {
        super(model, LAYOUT, ICON, TITLE);
        
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
