package database;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * This class model a tuple inside a db table
 */
public class Example implements Comparable<Example>{
	private List<Object> example=new ArrayList<Object>();

	/**
	 * Add a tuple of db
	 *@param o a tuple of a table's to insert 
	 */
	
	public void add(Object o){
		example.add(o);
	}
	
	/**
	 * Returns a tuple of db stored in one object of class Example
	 * @param i index of tuple to return
	 *@return a tuple of a example to return
	 */
	
	public Object get(int i){
		return example.get(i);
	}
	
	/**
	 * Compares the example with a given one
	 *@param ex the tuple of db to compare
	 *@return 0 if the two tuples are equals, otherwise a different number 
	 */
	
	public int compareTo(Example ex) {
		
		int i=0;
		for(Object o:ex.example){
			if(!o.equals(this.example.get(i)))
				return ((Comparable)o).compareTo(example.get(i));
			i++;
		}
		return 0;
	}
	
	/**
	 * Returns the entires tuples of db stored in object of class Example
	 *@return the entires tuples of db stored in object of class Example
	 */
	
	public String toString(){
		String str="";
		for(Object o:example)
			str+=o.toString()+ " ";
		return str;
	}
	
}