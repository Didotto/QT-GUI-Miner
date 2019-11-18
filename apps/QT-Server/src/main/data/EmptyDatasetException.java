package data;

/**
 *  This class handles an exception thrown when the dataset is empty
 */

public class EmptyDatasetException extends Exception {
	/**
	 * Builds an object of class EmptyDatasetException by calling the Exception constructor
	 *
	 */
	
	public EmptyDatasetException() {
		super("The Dataset is empty!");
	}
	
	/**
	 * Builds an object of class EmptyDatasetException with message given in input
	 * @param message the message of exception
	 */
	
	public EmptyDatasetException(String message) {
		super(message);
	}
}
