package data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ContinuousItemTest {

	private static ContinuousItem cItem;
	
	private static ContinuousAttribute cAttribute;
	
	private static final double VALUE = 10.0d;
	
	@BeforeAll
	static void setUpAll() {
		cAttribute = new ContinuousAttribute("AName", 0, 0.0d, 20.0d);
		assertAll(() -> {cItem = new ContinuousItem(cAttribute, VALUE);});
	}
	
	@Test
	void distanceTest() {
		ContinuousItem distItem = new ContinuousItem(cAttribute, 5.0d);
		assertEquals(0.25, cItem.distance(distItem));
	}
	
	@Test
	void getAttributeTest() {
		assertEquals(cAttribute, cItem.getAttribute());
	}
	
	@Test
	void getValueTest() {
		assertEquals(VALUE, cItem.getValue());
	}
	
	@Test
	void toStringTest() {
		assertEquals(new Double(VALUE).toString(), cItem.toString());
	}
	
}
