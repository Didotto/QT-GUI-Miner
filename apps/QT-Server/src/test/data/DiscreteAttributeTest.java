package data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DiscreteAttributeTest {
private static DiscreteAttribute dAttribute;
	
	private static final String NAME = "AttributeName";
	
	private static final int INDEX = 0;
	
	private static final TreeSet<String> VALUES = new TreeSet<String>();
	
	private static final int LENGTH = 3;
	@BeforeAll
	static void setUpAll() {
		for(int i=0; i<LENGTH; i++)
			VALUES.add("Value" + i);
		assertAll(() -> {dAttribute = new DiscreteAttribute(NAME, INDEX, VALUES);});
	}
	
	@Test
	void sizeTest() {
		assertEquals(LENGTH, dAttribute.getNumberOfDistinctValues());
	}
	
	@Test
	void iteratorTest() {
		assertEquals("Value0", dAttribute.iterator().next());
	}
	
	@Test
	void nameTest() {
		assertEquals(NAME, dAttribute.getName());
	}
	
	@Test
	void indexTest() {
		assertEquals(INDEX, dAttribute.getIndex());
	}
	
	@Test
	void toStringTest() {
		assertEquals(NAME, dAttribute.toString());
	}
}
