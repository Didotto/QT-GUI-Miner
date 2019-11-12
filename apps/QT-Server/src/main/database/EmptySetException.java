package database;

public class EmptySetException extends Exception {
	public EmptySetException() {
		//Default message
		super("The resultset is empty");
	}
	
	public EmptySetException(String message) {
		super(message);
	}
}
