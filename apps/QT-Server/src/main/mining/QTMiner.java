package mining;

import data.Data;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.util.Iterator;

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
}
