package view;


import model.DataModel;
import controller.MainController;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.control.SplitPane;

public class MainView extends View{
	private static final String TITLE = "QT-Miner JavaFX";
	
	private static final String ICON = "mining.png";
	
	private static final String LAYOUT = "main.fxml";
	
	public MainView(DataModel model) throws IOException {
		super(model, LAYOUT, ICON, TITLE);
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
