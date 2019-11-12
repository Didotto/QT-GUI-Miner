package data;

public class DiscreteItem extends Item {
	
	public DiscreteItem(DiscreteAttribute attribute, String value){
		super(attribute, value);
	}
	
	public double distance(Object a) {
		return (this.getValue().equals(((Item)a).getValue())) ? 0.0d : 1.0d; 
	}

}
