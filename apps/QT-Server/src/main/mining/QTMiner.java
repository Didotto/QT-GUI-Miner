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

public class QTMiner {
	private ClusterSet clusterSet;
	private double radius;
	
	public QTMiner(double radius) {
		this.radius = radius;
		this.clusterSet = new ClusterSet();
	}
	
	public QTMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(fileName));
		clusterSet = (ClusterSet) inStream.readObject();
		radius = inStream.readDouble();
		inStream.close();
	}
	
	public void save(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(fileName));
		outStream.writeObject(clusterSet);
		outStream.writeDouble(radius);
		outStream.close();
	}
	
	public ClusterSet getClusterSet() {
		return this.clusterSet;
	}
	
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
	
	public Cluster buildCandidateCluster(Data data, boolean isClustered[]) {
		
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
	
	public String toString() {
		return "Radius: " + radius + "\n" + clusterSet.toString();
	}
	
	public String toString(Data data) {
		return "Radius: " + radius + "\n" + clusterSet.toString(data);
	}
	
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
	
	public double getRadius() {
		return this.radius;
	}
}
