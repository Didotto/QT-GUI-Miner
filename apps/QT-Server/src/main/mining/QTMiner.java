package mining;

import data.Attribute;
import data.Data;
import data.Item;
import data.Tuple;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
//import java.util.Iterator;
import java.util.LinkedList;

/**
* This class defines how work QT 
*
*/
public class QTMiner {
	private ClusterSet clusterSet;
	private double radius;
	
	/**
	 * Initialize the radius and the ClusterSet
	 *
	 *@param radius the maximum distance between two groupable items
	 */
	public QTMiner(double radius) {
		this.radius = radius;
		this.clusterSet = new ClusterSet();
	}
	
	/**
	 * Retrieves the set cluster and the radius from a file
	 *
	 *@param fileName the name of the file in which an old cluster set was saved
	 *@throws FileNotFoundException if the file with the name "fileName" doesn't exists
	 *@throws IOException  if an I/O error occurs while reading stream header
	 *@throws ClassNotFoundException Class of a serialized object cannot be found.
	 */
	
	public QTMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(fileName));
		clusterSet = (ClusterSet) inStream.readObject();
		radius = inStream.readDouble();
		inStream.close();
	}
	/**
	 * Save ClusterSet and radius in a file
	 *
	 *@param fileName the name of the file in which a cluster set will be saved
	 *@throws FileNotFoundException if the file with the name "fileName" doesn't exists
	 *@throws IOException  if an I/O error occurs while reading stream header
	 *@throws ClassNotFoundException Class of a serialized object cannot be found.
	 */
	
	public void save(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(fileName));
		outStream.writeObject(clusterSet);
		outStream.writeDouble(radius);
		outStream.close();
	}
	
	/**
	 * Returns the ClusterSet
	 * @return the ClusterSet
	 */
	
	public ClusterSet getClusterSet() {
		return this.clusterSet;
	}
	
	/**
	 * Builds the Cluster Set exploiting buildCandidateCluster()
	 *@param data the data set
	 *@return number of clusters generated
	 *@throws ClusteringRadiusException if there is only one cluster
	 *
	 */
	
	public int compute(Data data) throws ClusteringRadiusException {
		int numClusters = 0;
		boolean isClustered[] = new boolean[data.getNumberOfExamples()];
		//all tuples are not clustered at the beginning
		for(int i=0; i<isClustered.length; i++)
			isClustered[i] = false;
		
		int countClustered = 0;
		while(countClustered != data.getNumberOfExamples()) {
			//find the most populous cluster
			Cluster c = buildCandidateCluster(data, isClustered);
			this.clusterSet.add(c);
			numClusters++;
			//removing clustered tuples from the dataset
			//int clusteredTupleId[] = c.iterator();			
			for(int it : c) {
				isClustered[it] = true;
			}
			
			countClustered += c.getSize();
		}
		if(numClusters == 1)
			throw new ClusteringRadiusException();
		return numClusters;
	}
	/**
	 * Builds a candidate cluster to add to the cluster set in this way : </br>
	 * for each non-clustered transaction, a cluster is created with the transaction as a centroid and all other </br>
	 * (non-clustered) transactions with a distance less than the radius are added to the cluster.</br>
	 *At the end of this procedure we will have clusters, we will choose the most populous cluster.</br>
	 *
	 *@param data the dataset
	 *@param isClustered array which summarizes the tuples already clastered
	 *@return candidate cluster
	 */
	private Cluster buildCandidateCluster(Data data, boolean isClustered[]) {
		
		ClusterSet candidates = new ClusterSet();
		for(int i = 0; i<data.getNumberOfExamples(); i++) {
			if(!isClustered[i]) {
				//Building a candidate
				Cluster c = new Cluster(data.getItemSet(i));
				for(int j=0; j<data.getNumberOfExamples(); j++) {
					if(!isClustered[j] && c.getCentroid().getDistance(data.getItemSet(j))<=this.radius) {
						//Adding a tuple to a candidate cluster if the distance is less then the radius
						c.addData(j);
					}
				}
				candidates.add(c);
			}
		}
		
		int max = -1;
		Cluster candidate = null;
		for(Cluster it : candidates) {
			if(it.getSize()>max) {
				max = it.getSize();
				candidate = it;
			}
		}
		return candidate;
	}
	
	/**
	 * Returns the radius and the cluster set as strings
	 *@return the radius and the cluster set as strings
	 */
	
	public String toString() {
		return "Radius: " + radius + "\n" + clusterSet.toString();
	}
	
	/**
	 * Returns the radius and the cluster set as strings (with all the items belonging to the dataset)
	 * @param data the entire dateset
	 *@return the radius and the cluster set as strings (with all the items belonging to the dataset)
	 */
	
	public String toString(Data data) {
		return "Radius: " + radius + "\n" + clusterSet.toString(data);
	}
	
	/**
	 * Builds a linked list (In the first position the "Cluster ID" was added) of Strings
	 * which represents the schema of the dataset.
	 *@return the scheme of data set in the form of a linked list of strings
	 */
	
	public LinkedList<String> getSchemeList(){
		LinkedList<String> attributeList = new LinkedList<String>();
		
		Iterator<Cluster> it = clusterSet.iterator();
		Cluster cluster = it.next();
		Tuple centroid = cluster.getCentroid();
		
		attributeList.add("Cluster ID");
		for(int i=0; i< centroid.getLength(); i++) {
			Item item = centroid.get(i);
			Attribute attribute = item.getAttribute();
			attributeList.add(attribute.getName());
		}
		return attributeList;
	}
	
	/**
	 * Builds a linked list of linked list of Strings 
	 * which represents a table of centroids
	 *@return a list of list of Strings which summarizes the list of centroids
	 */
	
	public LinkedList<LinkedList<String>> getCentroidsList(){
		LinkedList<LinkedList<String>> centroidList = new LinkedList<LinkedList<String>>();
		int i=0;
		for (Cluster c: clusterSet) {
			centroidList.add(new LinkedList<String>());
			centroidList.getLast().add(String.valueOf(i));
			Tuple t = c.getCentroid();
			for (int j=0;j<t.getLength();j++) {
				
				centroidList.getLast().add(t.get(j).getValue().toString());
			}
			i++;
		}
		return centroidList;
	}
	
	/**
	 * Builds a linked list of linked list of linked list of Strings
	 * which represents the entire dataset as a table
	 * @param data the entire dataset
	 *@return a list of list of list of Strings which summarizes the entire dataset
	 */
	
	public LinkedList<LinkedList<LinkedList<String>>> getDataList (Data data){
		LinkedList<LinkedList<LinkedList<String>>> tableList = new LinkedList<LinkedList<LinkedList<String>>>();
		int i=0;
		for(Cluster c: clusterSet) {
			tableList.add(new LinkedList<LinkedList<String>>());
			
			for(int tupleIndex: c) {
				tableList.getLast().add(new LinkedList<String>());
				tableList.getLast().getLast().add(String.valueOf(i));
				Tuple tuple = data.getItemSet(tupleIndex);
				for(int k=0; k<tuple.getLength(); k++) {
					tableList.getLast().getLast().add(tuple.get(k).toString());
				}
				tableList.getLast().getLast().add(((Double)tuple.getDistance(c.getCentroid())).toString());
			}
			i++;
		}
		
		return tableList;
	}
	
	/**
	*
	*Returns the radius of clusters
	*@return the radius of cluster set
	*/
	public double getRadius() {
		return this.radius;
	}
}
