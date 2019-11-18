package server;

import java.net.ServerSocket;

import java.net.Socket;
import java.io.IOException;


/**
 * Describes the operation of a multiserver
 */
public class MultiServer {
	private int port;
	private ServerSocket serversocket;
	
	/**
	 * Initializes a multiserver port and runs run()
	 *
	 *@param port the port on which the server is listening
	 *@throws IOException if it is unable to start the server
	 */
	public MultiServer(int port) throws IOException {
		this.port = port;
		run();
	}
	
	/**
	 * Instantiates a multiserver class object
	 *
	 *@param args string of arguments
	 */
	public static void main(String[] args) {
		System.out.println("Booting the server up");
		try {
			new MultiServer(12346);
		} catch(IOException e) {
			System.out.println("[!] Unable to start the server: " + e.getMessage());
		}
		
	}
	
	/**
	 * Instantiates a serversocket (object of class ServerSocket).</br>
	 * The server listens on the serverocket waiting for new connection requests.</br>
	 * When a connection request arrives, a work socket is created 
	 * (where the actual communication managed by another thread takes place).</br>
	 *All this is done to leave the serversocket free for other connection requests.</br>
	 *@throws IOException if an I/O error occurs when opening the socket.
	 */
	
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
