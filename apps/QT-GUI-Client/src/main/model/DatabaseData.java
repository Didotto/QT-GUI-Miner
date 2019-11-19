package model;

import java.util.LinkedList;

/**
 * This class models all the data available to the controls and views 
 * (when client asks the server to load the data from the database)
 */

public class DatabaseData extends Data{
	private String databaseTable;
		
	private LinkedList<LinkedList<LinkedList<String>>> data;

	/**
	 * Returns the name of database table 
	 * @return the name of database table 
	 */
	public String getDatabaseTable() {
		return databaseTable;
	}

	/**
	 * Set the name of database table (so as to make the information visible to view and controller)
	 * @param databaseTable the name of table to set
	 */
	
	public void setDatabaseTable(String databaseTable) {
		this.databaseTable = databaseTable;
	}

	/**
	 * Returns the data which represents the entire table returned by the server when the user requests to load the data from db
	 * @return the data which represents the entire table returned by the server
	 */
	
	public LinkedList<LinkedList<LinkedList<String>>> getData() {
		return data;
	}

	/**
	 * Set the data which represents the entire table returned by the server when the user requests to load the data from db
	 * with the data given in input
	 * @param data which represents the entire table returned by the server
	 */
	
	public void setData(LinkedList<LinkedList<LinkedList<String>>> data) {
		this.data = data;
	}
}
