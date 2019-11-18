package database;
/**
 * This class models an exception handler thrown when the connection to the db fails.
 *
 */
public class DatabaseConnectionException extends Exception {
	/**
	 * Initialize an object of class DatabaseConnectionException by calling the Exception constructor
	 *
	 */
	public DatabaseConnectionException() {
		super("Database connection failed!");
	}
	
	/**
	 * Initialize an object of class DatabaseConnectionException with message given in input
	 * @param message the message of exception
	 */
	public DatabaseConnectionException(String message) {
		super(message);
	}
}
