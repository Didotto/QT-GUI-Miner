package data;

/**
 * This class models a Continuous Attribute
 */

public class ContinuousAttribute extends Attribute{
	private double max;
	private double min;
	
	/**
	 * Builds an object of class ContinuousAttribute
	 * @param name the name of continuous attribute
	 * @param index the name of continuous attribute
	 * @param min the minimum double value of continuous attribute
	 * @param max the maximum double value of continuous attribute
	 */
	
	public ContinuousAttribute(String name, int index, double min, double max){
		super(name, index);
		this.min = min;
		this.max = max;
	}
	
	/**
	 * Computes and returns the normalized value of the parameter passed in input.
	 * @param v double value to normalize
	 * @return returns the normalized value of the parameter passed in input.
	 */
	
	public double getScaledValue(double v) {
		return (v-this.min)/(max-min);
	}
	
}
