package data;

public class EmptyDatasetException extends Exception {
	public EmptyDatasetException() {
		super("The Dataset is empty!");
	}
	
	public EmptyDatasetException(String message) {
		super(message);
	}
}
