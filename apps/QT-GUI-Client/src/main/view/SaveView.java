package view;

import java.io.IOException;
import javafx.stage.Modality;
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
