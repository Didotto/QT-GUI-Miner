package view;

import javafx.stage.Modality;
import java.io.IOException;
import model.DataModel;

public class GearView extends View {
	private static final String LAYOUT = "connectionOptions.fxml";
	
	private static final String ICON = "rotella.png";
	
	private static final String TITLE = "SETTINGS";
	
	public GearView(DataModel model) throws IOException {
		super(model, LAYOUT, ICON, TITLE);
		
		getStage().setResizable(false);
        getStage().initModality(Modality.APPLICATION_MODAL);
        getController().init(model, getStage());
        getStage().show();
	}
	
}
