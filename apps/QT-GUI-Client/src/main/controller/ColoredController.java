package controller;

import javafx.scene.paint.Color;

/**
 * This abstract class is a controller that handles colors
 */

public abstract class ColoredController extends Controller {

	/**
	 * Generate a pastel color based on integer
	 * @param i generic number
	 */
	
	protected Color generatePastelColor(int i) {
		return Color.hsb (
				(i * 67.0d) % 360.0d,
				(0.3d + ((i*0.01d)%0.1d)) ,
				0.8d);
	}

}
