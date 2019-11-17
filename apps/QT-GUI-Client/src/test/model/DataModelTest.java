package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DataModelTest {
	private static DataModel model;
	
	@BeforeAll
	static void setUpAll() {
		assertAll(() -> {model = new DataModel();});
	} 
	
	@Test
	void connectTest() {
		assertThrows(UnknownHostException.class , () -> {model.connect("senselessip", 12346);});
		assertThrows(IOException.class, () -> {model.connect("127.0.0.1", 45454);});
	}
	
	@Test
	void disconnectTest() {
		assertAll(() -> {model.disconnect();});
	}
	
	@Test
	void isConnectedTest() {
		assertFalse(model.isConnected());
	}
	
	@Test
	void inputOutputTest() {
		assertNull(model.getInputStream());
		assertNull(model.getOutputStream());
	}
	
	@Test
	void databaseDataTest() {
		DatabaseData dbData = new DatabaseData();
		assertAll(() -> {model.setDatabaseData(dbData);});
		assertEquals(dbData, model.getDatabaseData());
	}
	
	@Test
	void fileDataTest() {
		FileData fileData = new FileData();
		assertAll(() -> {model.setFileData(fileData);});
		assertEquals(fileData, model.getFileData());
	}
	
	@Test
	void isLoadDBTest() {
		assertAll(() -> {model.setLoadDB(true);});
		assertTrue(model.isLoadDB());
		assertAll(() -> {model.setLoadDB(false);});
		assertFalse(model.isLoadDB());
	}

}
