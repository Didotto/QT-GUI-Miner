package database;

public class DatabaseConnectionException extends Exception {
	
	public DatabaseConnectionException() {
		super("Database connection failed!");
	}
	
	public DatabaseConnectionException(String message) {
		super(message);
	}
}
