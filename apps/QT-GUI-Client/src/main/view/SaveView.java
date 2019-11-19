package view;

import java.io.IOException;
import javafx.stage.Modality;
import model.DataModel;

/**
 * This class models the view of the stage used
 * by the client to save the results to a file
 * chosen by the user
 */

public class SaveView extends View {
	private static final String LAYOUT = "saveOptions.fxml";
	
	private static final String ICON = "saveIcon.png";
	
	private static final String TITLE = "SAVE OPTIONS";
	
	/**
	 * Display the stage used to ask for the file name
	 * where the results will be stored
	 *
	 * @param model contains the information needed and stored for the client
	 * @throws IOException if an I/O error occurs when using the stage
	 */
	
	public SaveView(DataModel model) throws IOException {
		super(model, LAYOUT, ICON, TITLE);
        
        getStage().initModality(Modality.APPLICATION_MODAL);
        
        getController().init(model, getStage());
        getStage().setResizable(false);
        getStage().show();
	}
}
