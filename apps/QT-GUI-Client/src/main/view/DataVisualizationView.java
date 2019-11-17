package view;

import java.io.IOException;

import controller.DataVisualizationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DataModel;
import controller.ServerException;

public class DataVisualizationView extends View {
	private static final String LAYOUT = "dataVisualization.fxml";
	
	private static final String ICON = "mining.png";
	
	private static final String TITLE = "DATA VISUALIZATION";
	
	public DataVisualizationView(DataModel model, boolean isLoadDB) throws IOException {
        super(model, LAYOUT, ICON, TITLE);
        
        getStage().initModality(Modality.APPLICATION_MODAL);
        getController().init(model, getStage());
        
        AnchorPane root = (AnchorPane)getStage().getScene().getRoot();
        getStage().setMinWidth(root.getPrefWidth());
        getStage().setMinHeight(root.getPrefHeight());
        if (!isLoadDB) {
        	root.getChildren().get(1).setVisible(false);
        	root.getChildren().get(2).setVisible(false);
        }
        try {
        	((DataVisualizationController)getController()).updateTable(isLoadDB);
        	getStage().show();
        }catch (ServerException e) {
        	new AlertDialog(AlertType.ERROR,
					"ERROR",
					"ERROR",
					"File doesn't Exist!"
					);
        }
		
		
	}
}
