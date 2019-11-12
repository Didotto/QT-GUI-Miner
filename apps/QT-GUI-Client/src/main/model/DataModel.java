package model;

import java.net.Socket;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.UnknownHostException;
import java.io.IOException;
import java.util.Observable;

public class DataModel extends Observable{
	private Socket mySocket;
	
	private ObjectInputStream input;
	
	private ObjectOutputStream output;
	
	private String tableName;
	
	private Double radius;
	
	private String fileName;
	
	public DataModel() {
		mySocket = new Socket();
	}
	
	public void connect(String ipAddress, int port) throws IOException, UnknownHostException {
		InetSocketAddress endpoint = new InetSocketAddress(InetAddress.getByName(ipAddress), port);
		mySocket.connect(endpoint);
		output = new ObjectOutputStream(mySocket.getOutputStream());
		input = new ObjectInputStream(mySocket.getInputStream());
		setChanged();
		notifyObservers();
	}
	
	public boolean isConnected() {
		return mySocket.isConnected();
	}
	
	public ObjectInputStream getInputStream(){
		return input;
	}
	
	public ObjectOutputStream getOutputStream(){
		return output;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public void setRadius(Double radius) {
		this.radius = radius;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public Double getRadius() {
		return radius;
	}
}
