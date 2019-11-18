package mining;

import data.Data;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.io.Serializable;

/**
 * This class models a cluster set
 *
 *
 */

public class ClusterSet implements Iterable<Cluster>, Serializable {
	private Set<Cluster> clusters = new TreeSet<Cluster>();
	
	public ClusterSet() {
		
	}
	
	/**
	 * Add cluster in cluster set
	 *@param c clusters to be added to the cluster set
	 *
	 */
	public void add (Cluster c) {
		clusters.add(c);
	}
	/**
	 * Defines an iterator for cluster set
	 *@return the iterator for cluster set
	 *
	 */
	public Iterator<Cluster> iterator(){
		return clusters.iterator();
	}
	@Override
	/**
	 * Returns the set of centroids of clusterset (each centroid for each cluster) as strings
	*@return the set of centroids of clusterset (each centroid for each cluster) as strings
	*
	*/
	public String toString() {
		String str =  "";
		for(Cluster it : this)
			str += it.getCentroid().toString() + ", ";
		return str;
	}
	
	/**
	 * Returns  for each cluster in cluster set return :the centroid of the cluster,
	 *some example of tuple in the cluster,the distance between tuples and centroid 
	 *@param data the entire detaset 
	 *@return for each cluster in cluster set return :the centroid of the cluster,
	 *some example of tuple in the cluster,the distance between tuples and centroid 
	 *
	 */
	
	public String toString(Data data) {
		String str = "";
		int i=0;
		for(Cluster it : this) {
			if(this.clusters != null) {
				str += (i++) + ": " + it.toString(data) + "\n";
			}
		}
		return str;
	}
}
