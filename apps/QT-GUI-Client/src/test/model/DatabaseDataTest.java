package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DatabaseDataTest {

	private static DatabaseData dbData;
	
	@BeforeAll
	static void setUpAll() {
		assertAll(() -> {dbData = new DatabaseData();});
	}
	
	@Test
	void dbTableTest() {
		assertAll(() -> {dbData.setDatabaseTable("dbtable");});
		assertEquals("dbtable", dbData.getDatabaseTable());
	}
	
	@Test
	void radiusTest() {
		assertAll(() -> {dbData.setRadius(2.5);});
		assertEquals(2.5 , dbData.getRadius());
	}
	
	@Test
	void schemeTest() {
		LinkedList<String> scheme = new LinkedList<>();
		scheme.add("atb1");
		scheme.add("atb2");
		assertAll(() -> {dbData.setScheme(scheme);});
		assertEquals(scheme, dbData.getScheme());
	}
	
	@Test
	void dataTest() {
		LinkedList<LinkedList<LinkedList<String>>> data = new LinkedList<>();
		for(int i=0; i<3; i++) {
			data.add(new LinkedList<LinkedList<String>>());
			for(int j=0; j<2; j++) {
				data.getLast().add(new LinkedList<String>());
				for(int k=0; k<3; k++) {
					data.getLast().getLast().add("Cluster" + i + "Tuple" + j + "Attribute" + k);
				}
				
			}
				
		}
			
		assertAll(() -> {dbData.setData(data);});
		assertEquals(data, dbData.getData());
	}

}
