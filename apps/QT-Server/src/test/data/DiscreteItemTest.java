package data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DiscreteItemTest {
	private static DiscreteItem dItem;
	
	private static DiscreteAttribute dAttribute;
	
	private static final String VALUE = "value1";
	
	@BeforeAll
	static void setUpAll() {
		TreeSet<String> values = new TreeSet<>();
		values.add("value1");
		values.add("value2");
		dAttribute = new DiscreteAttribute("AName", 0, values);
		assertAll(() -> {dItem = new DiscreteItem(dAttribute, VALUE);});
	}
	
	@Test
	void distanceTest() {
		DiscreteItem distItem = new DiscreteItem(dAttribute, "value2");
		assertEquals(1.0d, dItem.distance(distItem));
		assertEquals(0.0d, dItem.distance(dItem));
	}
	
	@Test
	void getAttributeTest() {
		assertEquals(dAttribute, dItem.getAttribute());
	}
	
	@Test
	void getValueTest() {
		assertEquals(VALUE, dItem.getValue());
	}
	
	@Test
	void toStringTest() {
		assertEquals(VALUE, dItem.toString());
	}

}
