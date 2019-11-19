package controller;

import model.DataModel;
import javafx.stage.Stage;

public abstract class Controller {
	protected DataModel model;
	protected Stage controlledStage;
	public void init(DataModel model, Stage controlledStage) {
		if(this.model == null && this.controlledStage==null) {
			this.model = model;
			this.controlledStage = controlledStage;
		}
	}
}
