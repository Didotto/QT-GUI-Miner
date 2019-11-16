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

public class SaveView extends View {
	private static final String LAYOUT = "saveOptions.fxml";
	
	private static final String ICON = "saveIcon.png";
	
	private static final String TITLE = "SAVE OPTIONS";
	
	public SaveView(DataModel model) throws IOException {
		super(model, LAYOUT, ICON, TITLE);
        
        getStage().initModality(Modality.APPLICATION_MODAL);
        
        getController().init(model, getStage());
        getStage().setResizable(false);
        getStage().show();
	}
}
