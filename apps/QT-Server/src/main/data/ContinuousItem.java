package data;


public class ContinuousItem extends Item {
	
	public ContinuousItem(ContinuousAttribute attribute, Double value) {
		super(attribute, value);
	}
	
	
	public double distance(Object a) {
		ContinuousAttribute attribute = (ContinuousAttribute) this.getAttribute();
		return Math.abs(attribute.getScaledValue((double)this.getValue()) - attribute.getScaledValue((double)((ContinuousItem)a).getValue()));
	}

}
