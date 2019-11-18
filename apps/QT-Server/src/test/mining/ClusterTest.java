package mining;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import data.ContinuousAttribute;
import data.ContinuousItem;
import data.Data;
import data.DiscreteAttribute;
import data.DiscreteItem;
import data.EmptyDatasetException;
import data.Tuple;
import database.DatabaseConnectionException;
import database.NoValueException;
class ClusterTest {
	private static Tuple centroid;
	
	private static Cluster cluster;
	@BeforeAll
	static void setUpAll() {
		ContinuousAttribute cAttribute = new ContinuousAttribute("Attribute1", 0, 0.0d, 20.0d);
		DiscreteAttribute dAttribute = new DiscreteAttribute("Attribute2", 1, new TreeSet<String>(
				Arrays.asList(new String[] {"value1", "value2"})
				));
		ContinuousItem cItem = new ContinuousItem(cAttribute, 5.0d);
		DiscreteItem dItem = new DiscreteItem(dAttribute, "value1");
		centroid = new Tuple(2);
		centroid.add(cItem, 0);
		centroid.add(dItem, 1);
		assertAll(() -> {cluster = new Cluster(centroid);});
	}
	
	@Test
	void getCentroidTest() {
		assertEquals(centroid, cluster.getCentroid());
	}
	
	@Test
	void addRemoveSizeTest() {
		cluster.addData(5);
		assertAll(() -> cluster.addData(1));
		assertEquals(2, cluster.getSize());
		assertAll(() -> cluster.removeTuple(5));
		assertFalse(cluster.contain(5));
	}
	
	@Test
	void compareToTest() {
		Cluster smallerCluster = new Cluster(centroid);
		Cluster biggerCluster = new Cluster(centroid);
		for(int i=0; i<5; i++)
			biggerCluster.addData(i);
		assertEquals(1, cluster.compareTo(cluster));
		assertEquals(1, cluster.compareTo(smallerCluster));
		assertEquals(-1, cluster.compareTo(biggerCluster));
	}
	
	@Test
	void iteratorTest() {
		assertNotNull(cluster.iterator());
	}
	
	@Test
	void toSringTest() {
		assertNotNull("Centroid=(5.0value1)", cluster.toString());
	}
	
	@Test
	@Tag("DbConnection")
	void toSringDataTest() throws NoValueException, DatabaseConnectionException, SQLException, EmptyDatasetException {
		assertNotNull(cluster.toString(new Data("testtable")));
	}
}
