package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
*
* This class models a connection with a DB
*/

public class DbAccess {
	private String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	
	private final String DBMS = "jdbc:mysql";
	private final String SERVER = "localhost";
	private final String DATABASE = "MapDB";
	private final int PORT = 3306;
	private final String USER_ID = "MapUser";
	private final String PASSWORD = "map";
	
	private Connection conn;
	/*
	public DbAccess() {
		
	}
	*/
	/**
	 * Summarizes all the information useful for connecting to the database and attempts to connect
	 *@throws DatabaseConnectionException if the connection was not successful
	 */
	
	public void initConnection() throws DatabaseConnectionException {
		try {
			Class.forName(DRIVER_CLASS_NAME).newInstance();
		} catch(ClassNotFoundException e) {
			System.out.println("[!] Driver not found: " + e.getMessage());
			throw new DatabaseConnectionException();
		} catch(InstantiationException e){
			System.out.println("[!] Error during the instantiation : " + e.getMessage());
			throw new DatabaseConnectionException();
		} catch(IllegalAccessException e){
			System.out.println("[!] Cannot access the driver : " + e.getMessage());
			throw new DatabaseConnectionException();
		}
		
		try {
			
			String connectionString = DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE
					+ "?user=" + USER_ID + "&password=" + PASSWORD + "&serverTimezone=UTC";
			
			//String connectionString = DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE;
			System.out.println("Connection's String: " + connectionString);
			conn = DriverManager.getConnection(connectionString);
		} catch(SQLException e) {
			System.out.println("[!] SQLException: " + e.getMessage());
			System.out.println("[!] SQLState: " + e.getSQLState());
			System.out.println("[!] VendorError: " + e.getErrorCode());
			throw new DatabaseConnectionException();
		}
	}
	/**
	 * Return the reference to an object that shapes a connection
	 *@return the reference to an object that shapes a connection
	 *
	 */
	public Connection getConnection() {
		return conn;
	}
	
	/**
	 * Close a connection with db
	 *@throws SQLException if a database access error occurs
	 */
	
	public void closeConnection() throws SQLException{
		conn.close();
	}
}
