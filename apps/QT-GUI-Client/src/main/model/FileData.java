package model;

import java.util.LinkedList;

/**
 * This class models all the data available to the controls and views 
 * (when client asks the server to load the data from a file)
 */

public class FileData extends Data {
	private String fileName;
	
	private LinkedList<LinkedList<String>> data;

	/**
	 * Returns the file name from which you want to recover the previous clusterizations 
	 * @return the file name
	 */
	
	public String getFileName() {
		return fileName;
	}

	/**
	 * Set the file name with the input name given
	 * @param fileName the name of file from which you want to recover the previous clusterizations 
	 */
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Returns the data which represents the entire table returned by the server when the user requests to load the data from file
	 * @return the data which represents the entire table returned by the server
	 */
	
	public LinkedList<LinkedList<String>> getData() {
		return data;
	}

	/**
	 * Set the data which represents the entire table returned by the server when the user requests to load the data from file
	 * with the data given in input
	 * @param data the data which represents the entire table returned by the server
	 */
	
	public void setData(LinkedList<LinkedList<String>> data) {
		this.data = data;
	}
}
