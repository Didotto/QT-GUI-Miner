package data;

import java.util.Set;
import java.io.Serializable;

/**
 * This class represents a tuple as a sequence of attribute-value pairs (Items)
 */

public class Tuple implements Serializable{
	private Item tuple[];
	
	/**
	 * Builds an object instance of class Tuple with size given in input
	 * @param size the size tuple
	 */
	
	public Tuple(int size){
		this.tuple = new Item[size];
	}
	
	/**
	 * Returns the size of a tuple
	 * @return the size of a tuple
	 */
	
	public int getLength() {
		return tuple.length;
	}
	
	/**
	 * Returns the item corresponding to the position in a tuple given in input
	 * @param i index of item to return
	 * @return the item corresponding to the position in a tuple given in input
	 */
	
	public Item get(int i) {
		return tuple[i];
	}
	
	/**
	 * Add an item in the position given in input
	 * @param c the item to add
	 * @param i the position where to insert the item
	 */
	
	public void add(Item c, int i) {
		tuple[i] = c;
	}
	
	/**
	 * Calculates the distance between the tuple and an instance object of the Object class given as input
	 * @param obj the object to calculate the distance from the tuple
	 * @return the distance between the tuple and an instance object of the Object class given as input
	 */
	
	public double getDistance(Tuple obj) {
		double dist = 0.0d;
		for(int i = 0; i<this.getLength(); i++)
			dist += this.get(i).distance(obj.get(i));
		return dist;
	}
	
	/**
	 * Calculates the average distance between the  tuple and those obtainable from the rows of the dataset having an index in clusteredData.
	 * @param data the entire data set
	 * @param clusteredData set of index of tuple obtainable from dateset
	 * @return the distance between the  tuple and those obtainable from the rows of the dataset having an index in clusteredData.
	 */
	
	public double avgDistance(Data data, Set<Integer> clusteredData) {
		double sumD = 0.0d;
		for(int it : clusteredData) {
			sumD += this.getDistance(data.getItemSet(it));
		}
		return sumD / clusteredData.size();
	}
	
	/**
	 * Returns the tuple as string
	 * @return the tuple as string
	 */
	
	public String toString() {
		String str = "[";
		for(int i = 0; i<tuple.length-1; i++) {
			str += tuple[i].toString() + ", ";
		}
		str += tuple[tuple.length-1].toString() + "]";
		return str;
	}
}
