package model;

import java.net.Socket;
import java.net.InetAddress;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.UnknownHostException;
import java.io.IOException;
import java.util.Observable;

/**
 * This class models all the data available to the controls and views 
 * (when client asks the server to connect, or to exchange data)
 */

public class DataModel extends Observable{
	private Socket mySocket;
	
	private ObjectInputStream input;
	
	private ObjectOutputStream output;
	
	private FileData fileData;
	
	private DatabaseData databaseData;
	
	private boolean isLoadDB;
	
	/**
	 * Builds an object instance of class DataModel and initialize attributes
	 * 
	 */
	
	public DataModel() {
		mySocket = new Socket();
		fileData = new FileData();
		databaseData = new DatabaseData();
	}
	
	/**
	 * Create a socket and initialize the stream for the communication
	 * @param ipAddress ip address of the server
	 * @param port port on which the server is listening
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * @throws UnknownHostException Thrown to indicate that the IP address of a host could not be determined.
	 * 
	 */
	
	public void connect(String ipAddress, int port) throws IOException, UnknownHostException {
		mySocket = new Socket (InetAddress.getByName(ipAddress),port);
		output = new ObjectOutputStream(mySocket.getOutputStream());
		input = new ObjectInputStream(mySocket.getInputStream());
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Close the socket
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * 
	 */
	
	public void disconnect () throws IOException{
		mySocket.close();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Returns true if the client is connected to the server
	 * @return true if the client is connected to the server
	 * 
	 */
	
	public boolean isConnected() {
		return mySocket.isConnected()&&(!mySocket.isClosed());
	}
	
	/**
	 * Returns the input stream
	 * @return the input stream
	 */
	
	public ObjectInputStream getInputStream(){
		return input;
	}
	
	/**
	 * Returns the output stream
	 * @return the output stream
	 */
	
	public ObjectOutputStream getOutputStream(){
		return output;
	}

	/**
	 * Returns the reference to a FIleData class instance object 
	 * that summarizes all the useful information in case a clustering is loaded from file
	 * @return the reference to a FIle Data class instance object 
	 */
	
	public FileData getFileData() {
		return fileData;
	}

	/**
	 * Set the information in case a clustering is loaded from file
	 *@param fileData the reference to a FileData class instance 
	 */
	
	public void setFileData(FileData fileData) {
		this.fileData = fileData;
	}

	/**
	 * Returns the reference to a DatabaseData class instance object 
	 * that summarizes all the useful information in case a clustering is loaded from db
	 * @return the reference to a DatabaseData class instance object 
	 */
	
	public DatabaseData getDatabaseData() {
		return databaseData;
	}

	/**
	 * Set the information in case a clustering is loaded from db
	 *@param fileData the reference to a DatabaseData class instance 
	 */
	
	public void setDatabaseData(DatabaseData databaseData) {
		this.databaseData = databaseData;
	}

	/**
	 * Returns true if the client choose to load data from db, false otherwise
	 *@param true if the client choose to load data from db,false otherwise
	 */
	
	public boolean isLoadDB() {
		return isLoadDB;
	}

	/**
	 * Set true if the client choose to load data from db,false otherwise
	 *@param true if the client choose to load data from db,false otherwise
	 */
	
	public void setLoadDB(boolean isLoadDB) {
		this.isLoadDB = isLoadDB;
	}
	
}
