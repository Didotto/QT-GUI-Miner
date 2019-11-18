package data;

import java.io.Serializable;

/**
 * This abstract class models an generic Item
 *  
 */

public abstract class Item implements Serializable {
	private Attribute attribute;
	private Object value;
	
	/**
	 * Initializes an item with the attribute and a value
	 *  @param attribute to be assigned to the item
	 *  @param value value to be assigned to the item 
	 */
	
	public Item(Attribute attribute, Object value){
		this.attribute = attribute;
		this.value =  value;
	}
	
	/**
	 * Returns the attribute of an item 
	 * @return the attribute of an item
	 */
	
	public Attribute getAttribute() {
		return this.attribute;
	}
	
	/**
	 * Returns the value of an item 
	 * @return the value of an item 
	 */
	
	public Object getValue() {
		return this.value;
	}
	
	@Override
	
	/**
	 * Returns the item as string
	 * @return the item as string
	 */
	
	public String toString() {
		return this.value.toString();
	}
	
	/**
	 * Calculates the distance between an input object and this item
	 * @param a the object whose distance is to be calculated with this item
	 * @return distance between a and this item
	 */
	public abstract double distance(Object a);
}
