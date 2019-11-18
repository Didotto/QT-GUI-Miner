package mining;

import data.Tuple;
import data.Data;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.io.Serializable;

/**
 * This class models a cluster
 *
 */
public class Cluster implements Iterable<Integer>, Comparable<Cluster>, Serializable {
	private Tuple centroid;

	private Set<Integer> clusteredData; 
	
	/*Cluster(){
		
	}*/
	/**
	 * Initialize a cluster
	 * @param centroid centroid of the cluster 
	 */
	Cluster(Tuple centroid){
		this.centroid = centroid;
		this.clusteredData = new HashSet<Integer>();
		
	}
	/**
	 * Return centroid of the cluster 
	 *@return this.centroid centroid of the cluster 
	 */
	public Tuple getCentroid(){
		return this.centroid;
	}
	
	//return true if the tuple is changing cluster
	/**
	 * Add tuple with index id to the cluster
	 * @param id this is the index of the tuple to add to the cluster
	 * @return true if the tuple is changing cluster,false otherwise
	 */
	boolean addData(int id){
		return this.clusteredData.add(id);
		
	}
	
	//verifica se una transazione è clusterizzata nell'array corrente
	/**
	 * Verify if in one cluster there is the tuple with index id
	 * @param id this is the index of the tuple whose presence in a cluster is to be verified
	 * @return true if the tuple is in cluster ,false otherwise
	 */
	boolean contain(int id){
		return this.clusteredData.contains(id);
	}
	

	//remove the tuplethat has changed the cluster
	/**
	 * Remove tuple with index id from cluster
	 * @param id this is the index of the tuple to be removed in the cluster
	 */
	void removeTuple(int id){
		this.clusteredData.remove(id);
		
	}
	
	/**
	 * Return the cluster size
	 * @return the size of cluster (how many tuples there are in the cluster)
	 */
	
	public int getSize(){
		return this.clusteredData.size();
	}
	
	/**
	 * Return an iterator for cluster
	 * @return the iterator for clusteredData
	 */
	public Iterator<Integer> iterator(){
		return this.clusteredData.iterator();
	}
	
	/**
	 * Returns 1 if the size of the cluster is bigger than the size of c, -1 otherwise
	 * @param c a cluster
	 * @return 1 if the size of the cluster is bigger than the size of c, -1 otherwise
	 */
	
	public int compareTo(Cluster c) {
		return (clusteredData.size() >= c.getSize())? 1 : -1;
	}
	
	/**
	 * Return the centroid of cluster as string
	 * @return the centroid of cluster as string
	 */

	public String toString(){
		String str = "Centroid=(";
		for(int i = 0; i<centroid.getLength(); i++)
			str += centroid.get(i);
		str += ")";
		return str;
		
	}
	
	/**
	 * Return a string containing: the centroid of cluster, some tuple in the cluster and the distance between tuples and centroid
	 * @param data the data set
	 * @return a string containing: the centroid of cluster, some tuple in the cluster and the distance between tuples and centroid
	 */
	
	public String toString(Data data){
		String str = "Centroid=(";
		for(int i = 0; i<centroid.getLength(); i++)
			str += centroid.get(i)+ " ";
		str += ")\nExamples:\n";
		for(int i : this){
			str += "[";
			for(int j = 0; j<data.getNumberOfAttributes(); j++)
				str += data.getAttributeValue(i, j)+" ";
			str += "] dist=" + getCentroid().getDistance(data.getItemSet(i)) + "\n";
		}
		str += "\nAvgDistance="+getCentroid().avgDistance(data, clusteredData);
		return str;
		
	}

}
