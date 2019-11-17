package model;

import java.util.LinkedList;

public class DatabaseData extends Data{
	private String databaseTable;
		
	private LinkedList<LinkedList<LinkedList<String>>> data;

	public String getDatabaseTable() {
		return databaseTable;
	}

	public void setDatabaseTable(String databaseTable) {
		this.databaseTable = databaseTable;
	}

	public LinkedList<LinkedList<LinkedList<String>>> getData() {
		return data;
	}

	public void setData(LinkedList<LinkedList<LinkedList<String>>> data) {
		this.data = data;
	}
}
