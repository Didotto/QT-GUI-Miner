package controller;

/**
 * This class models an exception handler thrown when during the communication somethings goes wrong
 *
 */

public class ServerException extends Exception {
	
	/**
	 * Initialize an object of class ServerException by calling the Exception constructor
	 *
	 */
	
	public ServerException() {
		super("Error occurred while communicating with the server");
	}
	
	/**
	 * Initialize an object of class DatabaseConnectionException with message given in input
	 * @param message the message of exception
	 */
	
	public ServerException(String message) {
		super(message);
	}
}
