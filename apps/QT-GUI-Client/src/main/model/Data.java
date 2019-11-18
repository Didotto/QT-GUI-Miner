package model;

import java.util.LinkedList;

/**
 * This abstract class models the data available to the controls and views 
 * (when client asks the server to load the data from the database or from file)
 */

public abstract class Data {
	private Double radius;
	
	private LinkedList<String> scheme;

	/**
	 * Returns the radius 
	 * @return the radius 
	 */
	
	public Double getRadius() {
		return radius;
	}

	/**
	 * Sets the input value as the radius
	 * @param radius the input value 
	 */
	
	public void setRadius(Double radius) {
		this.radius = radius;
	}

	/**
	 * Return the scheme of data sent from server 
	 * @return the scheme of data sent from server 
	 */
	
	public LinkedList<String> getScheme() {
		return scheme;
	}

	/**
	 * Set the scheme of data sent from server 
	 * @param scheme the scheme sent from server
	 */
	
	public void setScheme(LinkedList<String> scheme) {
		this.scheme = scheme;
	}
	
}
