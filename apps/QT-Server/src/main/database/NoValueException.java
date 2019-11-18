package database;

/**
 * This class handles an exception thrown when there are no value retrived from column in db when it's used aggregate operator
 *
 */

public class NoValueException extends Exception {
	public NoValueException() {
		//Default message
		super("There is no value inside the resultset");
	}
	
	public NoValueException(String message) {
		super(message);
	}
}
