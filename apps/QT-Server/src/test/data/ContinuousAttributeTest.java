package data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ContinuousAttributeTest {
	
	private static ContinuousAttribute cAttribute;
	
	private static final String NAME = "AttributeName";
	
	private static final int INDEX = 0;
	
	private static final double MIN = 0.0d;
	
	private static final double MAX = 20.0d;
	
	@BeforeAll
	static void setUpAll() {
		assertAll(() -> {cAttribute = new ContinuousAttribute(NAME, INDEX, MIN, MAX);});
	}
	
	@Test
	void scaledValueTest() {
		assertEquals(0.25d, cAttribute.getScaledValue(5));
	}
	
	@Test
	void nameTest() {
		assertEquals(NAME, cAttribute.getName());
	}
	
	@Test
	void indexTest() {
		assertEquals(INDEX, cAttribute.getIndex());
	}
	
	@Test
	void toStringTest() {
		assertEquals(NAME, cAttribute.toString());
	}
}
