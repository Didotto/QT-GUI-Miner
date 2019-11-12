package mining;

public class ClusteringRadiusException extends Exception{
	public ClusteringRadiusException() {
		super("Radius is too big. All tuples in one Cluster!");
	}
	
	public ClusteringRadiusException(String message) {
		super(message);
	}
}
