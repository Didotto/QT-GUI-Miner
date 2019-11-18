package data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TupleTest {
	private static Tuple tuple;
	
	private static DiscreteItem dItem;
	
	private static ContinuousItem cItem;
	
	private static ContinuousAttribute cAttribute;
	
	private static DiscreteAttribute dAttribute;
	
	@BeforeAll
	static void setUpAll() {
		cAttribute = new ContinuousAttribute("Attribute1", 0, 0.0d, 20.0d);
		dAttribute = new DiscreteAttribute("Attribute2", 1, new TreeSet<String>(
				Arrays.asList(new String[] {"value1", "value2"})
				));
		cItem = new ContinuousItem(cAttribute, 5.0d);
		dItem = new DiscreteItem(dAttribute, "value1");
		assertAll(() -> {tuple = new Tuple(2);});
		assertAll(() -> {tuple.add(cItem, 0);});
		assertAll(() -> {tuple.add(dItem, 1);});
	}
	
	@Test
	void lengthTest() {
		assertEquals(2, tuple.getLength());
	}
	
	@Test
	void distanceTest() {
		Tuple distTuple = new Tuple(2);
		distTuple.add(new ContinuousItem(cAttribute, 5.0d), 0);
		distTuple.add(new DiscreteItem(dAttribute, "value2"), 1);
		assertEquals(1.0d, tuple.getDistance(distTuple));
	}
	
	@Test
	void getTest() {
		assertEquals(cItem, tuple.get(0));
		assertEquals(dItem, tuple.get(1));
	}
	
	@Test
	void toStringTest() {
		assertEquals("[" + cItem.toString() + ", " + dItem.toString() + "]", tuple.toString());
	}

}
