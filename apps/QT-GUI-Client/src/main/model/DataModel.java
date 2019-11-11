package model;

import java.net.Socket;
import java.net.InetSocketAddress;
import java.net.InetAddress;

import java.net.UnknownHostException;
import java.io.IOException;
import java.util.Observable;

public class DataModel extends Observable{
	private Socket mySocket;
	
	public DataModel() {
		mySocket = new Socket();
	}
	
	public void connect(String ipAddress, int port) throws IOException, UnknownHostException {
		InetSocketAddress endpoint = new InetSocketAddress(InetAddress.getByName(ipAddress), port);
		mySocket.connect(endpoint);
		setChanged();
		notifyObservers();
	}
	
	public boolean isConnected() {
		return mySocket.isConnected();
	}
}
