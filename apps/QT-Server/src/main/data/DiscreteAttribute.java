package data;

import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

/**
 * This class models a Discrete Attribute
 */

public class DiscreteAttribute extends Attribute implements Iterable<String>{
	private Set<String> values;
	
	/**
	 * Builds an object of class Discrete Attribute 
	 * @param name the name of the attribute
	 * @param index the index associated to the attribute
	 * @param values a set of values assumed by an attribute
	 */
	
	public DiscreteAttribute(String name, int index, TreeSet<String> values){
		super(name, index);
		this.values = values;
	}
	
	/**
	 * Returns the number of number of distinct values assumed by an attribute
	 * @return the number of number of distinct values assumed by an attribute
	 */
	
	public int getNumberOfDistinctValues() {
		return values.size();
	}
	
	/**
	 * Returns an iterator for the set of value
	 * @return an iterator for the set of value
	 */
	
	public Iterator<String> iterator() {
		return values.iterator();
	}
}
