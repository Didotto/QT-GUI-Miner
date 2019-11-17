package model;

import java.util.LinkedList;

public abstract class Data {
	private Double radius;
	
	private LinkedList<String> scheme;

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public LinkedList<String> getScheme() {
		return scheme;
	}

	public void setScheme(LinkedList<String> scheme) {
		this.scheme = scheme;
	}
	
}
