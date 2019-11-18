package data;

public class ContinuousAttribute extends Attribute{
	private double max;
	private double min;
	
	public ContinuousAttribute(String name, int index, double min, double max){
		super(name, index);
		this.min = min;
		this.max = max;
	}
	
	public double getScaledValue(double v) {
		return (v-this.min)/(max-min);
	}
	
}
