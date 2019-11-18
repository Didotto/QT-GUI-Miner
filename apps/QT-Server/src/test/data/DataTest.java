package data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DataTest {
	private static Data data;
	
	private static final String TABLE_NAME = "testtable";
	
	@BeforeAll
	@Tag("DbConnection")
	static void setUpAll() {
		assertAll(() -> {data = new Data(TABLE_NAME);});
		assertThrows(SQLException.class, () -> new Data("unsavedtable"));
		assertThrows(EmptyDatasetException.class, () -> new Data("emptytable"));
	}
	
	@Test
	@Tag("DbConnection")
	void getNumberExampleTest() {
		assertEquals(3, data.getNumberOfExamples());
	}
	
	@Test
	@Tag("DbConnection")
	void getNumberAttributesTest() {
		assertEquals(2, data.getNumberOfAttributes());
	}
	
	@Test
	@Tag("DbConnection")
	void getValueTest() {
		assertEquals("value1", data.getAttributeValue(1, 0));
	}
	
	@Test
	@Tag("DbConnection")
	void getAttributeSchemaTest() {
		assertEquals(Arrays.asList("dAttribute", "cAttribute").toString(),data.getAttributeSchema().toString());
	}
	
	@Test
	@Tag("DbConnection")
	void toStringTest() {
		String equals = "dAttribute, cAttribute, \n" +
				"1: value1, 5.0, \n" + 
				"2: value1, 5.5, \n" + 
				"3: value2, 8.5, \n";
		assertEquals(equals, data.toString());
	}
	
	@Test
	@Tag("DbConnection")
	void attributeTest() {
		assertEquals("dAttribute", data.getAttribute(0).getName());
	}
	
	@Test
	@Tag("DbConnection")
	void getTupleTest() {
		assertEquals("[value1, 5.0]", data.getItemSet(0).toString());
	}
	
	

}
