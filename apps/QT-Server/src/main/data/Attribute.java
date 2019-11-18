package data;

import java.io.Serializable;

/**
 * This abstract class models an generic attribute 
 */

public abstract class Attribute implements Serializable {
	private String name;
	private int index;
	
	/**
	 * Initializes an attribute with the name and index specified  
	 * @param name the name of attribute
	 * @param index the index associated to one specified attribute
	 * 
	 */
	
	public Attribute(String name, int index){
		this.name = name;
		this.index = index;
	}
	
	/**
	 * Returns the name of an attribute 
	 * @return the name of an attribute  
	 */
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the index of an attribute 
	 * @return the index of an attribute  
	 */
	
	public int getIndex() {
		return this.index;
	}
	
	/**
	 * Returns an attribute as string 
	 * @return an attribute as string 
	 */
	public String toString() {
		return name;
	}
}