package view;


import model.DataModel;
import controller.MainController;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.control.SplitPane;

/**
 * This class models the view of the initial stage
 * used by the client to perform the next operations
 */

public class MainView extends View{
	/**
	 * Display the initial stage to the client and disable
	 * or show some elements based on the user's decision
	 *
	 * @param model contains the information needed and stored for the client
	 * @throws IOException if an I/O error occurs when using the stage
	 */
	
	
	public MainView(DataModel model) throws IOException {
		super(model, "main.fxml", "mining.png", "QT-Miner JavaFX");
		MainController controller = (MainController)getController();
		model.addObserver(controller);
		SplitPane splitPane = (SplitPane)getStage().getScene().lookup("#splitPane");
        
        getStage().setMinHeight(500);
        getStage().setMinWidth(500);
        
        getStage().setOnHidden(e -> Platform.exit());
        
        controller.init(model, getStage());
        getStage().show();
        splitPane.lookupAll(".split-pane-divider").stream()
			.forEach(div ->  div.setMouseTransparent(true));
	}
}
