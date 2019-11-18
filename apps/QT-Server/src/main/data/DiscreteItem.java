package data;

/**
 * This class models a Discrete Item
 *  
 */

public class DiscreteItem extends Item {
	
	/**
	 * Builds an object instance of class Discrete Item by calling the constructor of super class
	 * @param attribute the attribute with which to initialize the item
	 * @param value the value with which to initialize the item
	 */
	public DiscreteItem(DiscreteAttribute attribute, String value){
		super(attribute, value);
	}
	
	/**
	 * Calculates the distance between the input object and the item itself
	 * @param a the object whose distance is to be calculated
	 * @return 0 if the item and the object given in input are equals , 1 otherwise.
	 */
	
	public double distance(Object a) {
		return (this.getValue().equals(((Item)a).getValue())) ? 0.0d : 1.0d; 
	}

}
