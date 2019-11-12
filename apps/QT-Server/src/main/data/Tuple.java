package data;

import java.util.Set;
import java.io.Serializable;

public class Tuple implements Serializable{
	private Item tuple[];
	
	Tuple(int size){
		this.tuple = new Item[size];
	}
	
	public int getLength() {
		return tuple.length;
	}
	
	public Item get(int i) {
		return tuple[i];
	}
	
	public void add(Item c, int i) {
		tuple[i] = c;
	}
	
	public double getDistance(Tuple obj) {
		double dist = 0.0d;
		for(int i = 0; i<this.getLength(); i++)
			dist += this.get(i).distance(obj.get(i));
		return dist;
	}
	
	public double avgDistance(Data data, Set<Integer> clusteredData) {
		double sumD = 0.0d;
		for(int it : clusteredData) {
			sumD += this.getDistance(data.getItemSet(it));
		}
		return sumD / clusteredData.size();
	}
	
	public String toString() {
		String str = "[";
		for(int i = 0; i<tuple.length-1; i++) {
			str += tuple[i].toString() + ", ";
		}
		str += tuple[tuple.length-1].toString() + "]";
		return str;
	}
}
