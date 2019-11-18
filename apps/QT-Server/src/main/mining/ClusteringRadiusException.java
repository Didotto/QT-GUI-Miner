package mining;
/**
 * This class model a handler for an exception thrown when in class QTMiner there is generates only one cluster
 */
public class ClusteringRadiusException extends Exception{
	
	/**
	 * Builds an object of class ClusteringRadiusException by calling the Exception constructor
	 *
	 */
	
	public ClusteringRadiusException() {
		super("Radius is too big. All tuples in one Cluster!");
	}
	/**
	 * Builds an object of class ClusteringRadiusException with message given in input
	 * @param message the message of exception
	 */
	public ClusteringRadiusException(String message) {
		super(message);
	}
}
