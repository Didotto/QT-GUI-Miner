package model;

import java.util.LinkedList;

public class FileData extends Data {
	private String fileName;
	
	private LinkedList<LinkedList<String>> data;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LinkedList<LinkedList<String>> getData() {
		return data;
	}

	public void setData(LinkedList<LinkedList<String>> data) {
		this.data = data;
	}
}
