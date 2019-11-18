package data;

/**
 * This class models a Continuous Item
 *  
 */
public class ContinuousItem extends Item {
	
	/**
	 * Builds an object instance of class Continuous Item by calling the constructor of super class
	 * @param attribute the attribute with which to initialize the item
	 * @param value the value with which to initialize the item
	 */
	
	public ContinuousItem(ContinuousAttribute attribute, Double value) {
		super(attribute, value);
	}
	
	/**
	 * Calculates the distance between the input object and the item itself
	 * @param a the object whose distance is to be calculated
	 * @return the distance between the input object and the item itself
	 */
	public double distance(Object a) {
		ContinuousAttribute attribute = (ContinuousAttribute) this.getAttribute();
		return Math.abs(attribute.getScaledValue((double)this.getValue()) - attribute.getScaledValue((double)((ContinuousItem)a).getValue()));
	}

}
