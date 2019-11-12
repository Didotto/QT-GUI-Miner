package controller;

public class ServerException extends Exception {
	
	public ServerException() {
		super("Error occurred while communicating with the server");
	}
	
	public ServerException(String message) {
		super(message);
	}
}
