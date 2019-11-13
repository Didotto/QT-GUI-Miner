package mining;

import data.Tuple;
import data.Data;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.io.Serializable;

public class Cluster implements Iterable<Integer>, Comparable<Cluster>, Serializable {
	private Tuple centroid;

	private Set<Integer> clusteredData; 
	
	/*Cluster(){
		
	}*/

	Cluster(Tuple centroid){
		this.centroid = centroid;
		this.clusteredData = new HashSet<Integer>();
		
	}
		
	public Tuple getCentroid(){
		return this.centroid;
	}
	
	//return true if the tuple is changing cluster
	boolean addData(int id){
		return this.clusteredData.add(id);
		
	}
	
	//verifica se una transazione è clusterizzata nell'array corrente
	boolean contain(int id){
		return this.clusteredData.contains(id);
	}
	

	//remove the tuplethat has changed the cluster
	void removeTuple(int id){
		this.clusteredData.remove(id);
		
	}
	
	public int getSize(){
		return this.clusteredData.size();
	}
	
	
	public Iterator<Integer> iterator(){
		return this.clusteredData.iterator();
	}
	
	public int compareTo(Cluster c) {
		return (clusteredData.size() >= c.getSize())? 1 : -1;
	}
	
	public String toString(){
		String str = "Centroid=(";
		for(int i = 0; i<centroid.getLength(); i++)
			str += centroid.get(i);
		str += ")";
		return str;
		
	}
	
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
