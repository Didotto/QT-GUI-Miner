package data;

import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class DiscreteAttribute extends Attribute implements Iterable<String>{
	private Set<String> values;
	
	public DiscreteAttribute(String name, int index, TreeSet<String> values){
		super(name, index);
		this.values = values;
	}
	
	public int getNumberOfDistinctValues() {
		return values.size();
	}
	
	public Iterator<String> iterator() {
		return values.iterator();
	}
}
