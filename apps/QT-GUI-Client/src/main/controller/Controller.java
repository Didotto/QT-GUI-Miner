package controller;

import model.DataModel;
import javafx.stage.Stage;

/**
 * This abstract class rappresent a generic controller for views
 */

public abstract class Controller {
	protected DataModel model;
	protected Stage controlledStage;
	
	/**
	 * Initialize the attributes
	 * @param model the data owned by the client
	 * @param controlledStage the specific cotrolled stage
	 */
	
	public void init(DataModel model, Stage controlledStage) {
		if(this.model == null && this.controlledStage==null) {
			this.model = model;
			this.controlledStage = controlledStage;
		}
	}
}
