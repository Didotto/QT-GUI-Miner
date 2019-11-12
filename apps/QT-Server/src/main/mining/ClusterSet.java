package mining;

import data.Data;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.io.Serializable;

public class ClusterSet implements Iterable<Cluster>, Serializable {
	private Set<Cluster> clusters = new TreeSet<Cluster>();
	
	public ClusterSet() {
		
	}
	
	public void add (Cluster c) {
		clusters.add(c);
	}
	
	public Iterator<Cluster> iterator(){
		return clusters.iterator();
	}
	
	public String toString() {
		String str =  "";
		for(Cluster it : this)
			str += it.getCentroid().toString() + ", ";
		return str;
	}
	
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
