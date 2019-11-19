package view;

import javafx.stage.Modality;
import java.io.IOException;
import model.DataModel;

/**
 * This class models the view of the stage
 * used by the client to access the settings
 * in order to set up a connection
 */

public class GearView extends View {
	private static final String LAYOUT = "connectionOptions.fxml";
	
	private static final String ICON = "rotella.png";
	
	private static final String TITLE = "SETTINGS";
	
	/**
	 * Display the stage to the client where 
	 * it will choose the connection's settings
	 *
	 * @param model contains the information needed and stored for the client
	 * @throws IOException if an I/O error occurs when using the stage
	 */
	
	public GearView(DataModel model) throws IOException {
		super(model, LAYOUT, ICON, TITLE);
		
		getStage().setResizable(false);
        getStage().initModality(Modality.APPLICATION_MODAL);
        getController().init(model, getStage());
        getStage().show();
	}
	
}
