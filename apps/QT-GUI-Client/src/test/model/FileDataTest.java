package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileDataTest {

private static FileData fileData;
	
	@BeforeAll
	static void setUpAll() {
		assertAll(() -> {fileData = new FileData();});
	}
	
	@Test
	void fileNameTest() {
		assertAll(() -> {fileData.setFileName("filename");});
		assertEquals("filename", fileData.getFileName());
	}
	
	@Test
	void radiusTest() {
		assertAll(() -> {fileData.setRadius(2.5);});
		assertEquals(2.5 , fileData.getRadius());
	}
	
	@Test
	void schemeTest() {
		LinkedList<String> scheme = new LinkedList<>();
		scheme.add("atb1");
		scheme.add("atb2");
		assertAll(() -> {fileData.setScheme(scheme);});
		assertEquals(scheme, fileData.getScheme());
	}
	
	@Test
	void dataTest() {
		LinkedList<LinkedList<String>> data = new LinkedList<>();
		for(int i=0; i<2; i++) {
			data.add(new LinkedList<String>());
			for(int j=0; j<3; j++) {
				data.getLast().add("Centroid" + i + "Attribute" + j);
			}
				
		}
			
		assertAll(() -> {fileData.setData(data);});
		assertEquals(data, fileData.getData());
	}

}
