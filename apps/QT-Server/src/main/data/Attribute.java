package data;

import java.io.Serializable;

public abstract class Attribute implements Serializable {
	private String name;
	private int index;
	
	Attribute(String name, int index){
		this.name = name;
		this.index = index;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public String toString() {
		return name;
	}
}