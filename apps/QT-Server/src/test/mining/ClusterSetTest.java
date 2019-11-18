package mining;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import data.Data;
import data.EmptyDatasetException;
import database.DatabaseConnectionException;
import database.NoValueException;

class ClusterSetTest {
	private static ClusterSet clusterSet;
	
	private static Cluster cluster;
	
	private static QTMiner miner;
	
	private static Data data;
	@BeforeAll
	static void setUpAll() {
		miner = new QTMiner(1.0d);
		try {
			data = new Data("testtable");
		} catch (NoValueException | DatabaseConnectionException | SQLException
				| EmptyDatasetException e) {
			e.printStackTrace();
		}
		try {
			miner.compute(data);
		} catch (ClusteringRadiusException e) {
			e.printStackTrace();
		}
		clusterSet = miner.getClusterSet();
		cluster = clusterSet.iterator().next();
		assertAll(() -> {new ClusterSet();});
	}
	
	
	@Test
	void addTest() {
		assertAll(() -> new ClusterSet().add(cluster));
	}
	
	@Test
	void iteratorTest() {
		assertEquals(cluster, clusterSet.iterator().next());
	}
	
	@Test
	void toStringTest() {
		assertEquals("[value2, 8.5], [value1, 5.0], ", clusterSet.toString());
	}
	
	@Test
	@Tag("DbConnection")
	void toStringDataTest() {
		String equals = "0: Centroid=(value2 8.5 )\n" + 
				"Examples:\n" + 
				"[value2 8.5 ] dist=0.0\n" + 
				"\n" + 
				"AvgDistance=0.0\n" + 
				"1: Centroid=(value1 5.0 )\n" + 
				"Examples:\r\n" + 
				"[value1 5.0 ] dist=0.0\n" + 
				"[value1 5.5 ] dist=0.14285714285714285\n" + 
				"\n" + 
				"AvgDistance=0.07142857142857142\n";
		assertNotNull(equals, clusterSet.toString(data));
	}
}
