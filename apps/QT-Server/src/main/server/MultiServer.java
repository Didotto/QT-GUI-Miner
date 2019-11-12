package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class MultiServer {
	private int port;
	private ServerSocket serversocket;
	
	public MultiServer(int port) throws IOException {
		this.port = port;
		run();
	}
	
	public static void main(String[] args) {
		System.out.println("Booting the server up");
		try {
			new MultiServer(12346);
		} catch(IOException e) {
			System.out.println("[!] Unable to start the server: " + e.getMessage());
		}
		
	}
	
	private void run() throws IOException {
		serversocket = new ServerSocket(this.port);
		try {
			while(true) {
				Socket newClient = serversocket.accept();
				System.out.println("New client arrived: " + newClient);
				try {
					new ServeOneClient(newClient);
				} catch (IOException e) {
					System.out.println("Connection to client lost: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println("[!] Error occurred while communicating with the client: " + e.getMessage());
		} finally {
			System.out.println("Shutting down the server");
			serversocket.close();
		}
	}
}
