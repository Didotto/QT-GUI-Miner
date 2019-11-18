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
	 * Builds an object instance of class DataModel and initialize attributes
	 * 
	 */
	
	public void connect(String ipAddress, int port) throws IOException, UnknownHostException {
		mySocket = new Socket (InetAddress.getByName(ipAddress),port);
		output = new ObjectOutputStream(mySocket.getOutputStream());
		input = new ObjectInputStream(mySocket.getInputStream());
		setChanged();
		notifyObservers();
	}
	
	public void disconnect () throws IOException{
		mySocket.close();
		setChanged();
		notifyObservers();
	}
	
	public boolean isConnected() {
		return mySocket.isConnected()&&(!mySocket.isClosed());
	}
	
	public ObjectInputStream getInputStream(){
		return input;
	}
	
	public ObjectOutputStream getOutputStream(){
		return output;
	}

	public FileData getFileData() {
		return fileData;
	}

	public void setFileData(FileData fileData) {
		this.fileData = fileData;
	}

	public DatabaseData getDatabaseData() {
		return databaseData;
	}

	public void setDatabaseData(DatabaseData databaseData) {
		this.databaseData = databaseData;
	}

	public boolean isLoadDB() {
		return isLoadDB;
	}

	public void setLoadDB(boolean isLoadDB) {
		this.isLoadDB = isLoadDB;
	}
	
}
