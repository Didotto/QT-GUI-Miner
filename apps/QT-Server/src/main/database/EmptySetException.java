package database;

/**
 * This class handles an exception thrown when there are no transaction retrived from db
 *
 */

public class EmptySetException extends Exception {
	/**
	 * Builds an object of class EmptySetException by calling the Exception constructor
	 *
	 */
	public EmptySetException() {
		//Default message
		super("The resultset is empty");
	}
	/**
	 * Builds an object of class EmptySetException with message given in input
	 * @param message the message of exception
	 */
	public EmptySetException(String message) {
		super(message);
	}
}
