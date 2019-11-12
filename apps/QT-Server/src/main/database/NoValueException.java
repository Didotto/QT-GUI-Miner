package database;

public class NoValueException extends Exception {
	public NoValueException() {
		//Default message
		super("There is no value inside the resultset");
	}
	
	public NoValueException(String message) {
		super(message);
	}
}
