package mining;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import data.Data;
import data.EmptyDatasetException;
import database.DatabaseConnectionException;
import database.NoValueException;

class QTMinerTest {
	private static QTMiner miner;
	
	private static final double RADIUS = 1.0d; 
	
	private static Data data;
	@BeforeAll
	@Tag("DbConnection")
	static void setUpAll() {
		try {
			data = new Data("testtable");
		} catch (NoValueException | DatabaseConnectionException | SQLException
				| EmptyDatasetException e) {
			e.printStackTrace();
		}
		assertAll(() -> {miner = new QTMiner(RADIUS);});
		try {
			miner.compute(data);
		} catch (ClusteringRadiusException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Tag("DbConnection")
	void computeTest() {
		try {
			assertEquals(2, miner.compute(data));
		} catch (ClusteringRadiusException e) {
			e.printStackTrace();
		}
		assertThrows(ClusteringRadiusException.class,() -> new QTMiner(50.0d).compute(data));
	}
	
	@Test
	@Tag("DbConnection")
	void saveTest() {
		assertAll(() -> miner.save("test.dmp"));
	}
	
	@Test
	@Tag("DbConnection")
	void loadTest() {
		assertAll(() -> new QTMiner("test.dmp"));
	}
	
	@Test
	@Tag("DbConnection")
	void getRadiusTest() {
		assertEquals(RADIUS, miner.getRadius());
	}
	
	@Test
	@Tag("DbConnection")
	void getSchemeListTest() {
		List<String> scheme = Arrays.asList("Cluster ID", "dAttribute", "cAttribute");
		assertEquals(scheme, miner.getSchemeList());
	}
	
	@Test
	@Tag("DbConnection")
	void getDataListTest() {
		LinkedList<LinkedList<LinkedList<String>>> dataList = new LinkedList<>();
		dataList.add(new LinkedList<>());
		dataList.getLast().add(new LinkedList<>());
		dataList.getLast().getLast().add("0");
		dataList.getLast().getLast().add("value2");
		dataList.getLast().getLast().add("8.5");
		dataList.getLast().getLast().add("0.0");
		dataList.add(new LinkedList<>());
		dataList.getLast().add(new LinkedList<>());
		dataList.getLast().getLast().add("1");
		dataList.getLast().getLast().add("value1");
		dataList.getLast().getLast().add("5.0");
		dataList.getLast().getLast().add("0.0");
		dataList.getLast().add(new LinkedList<>());
		dataList.getLast().getLast().add("1");
		dataList.getLast().getLast().add("value1");
		dataList.getLast().getLast().add("5.5");
		dataList.getLast().getLast().add("0.14285714285714285");
		assertEquals(dataList, miner.getDataList(data));
	}
	
	@Test
	@Tag("DbConnection")
	void getCentroidListTest() {
		LinkedList<LinkedList<String>> centroidList = new LinkedList<>();
		centroidList.add(new LinkedList<>());
		centroidList.getLast().add("0");
		centroidList.getLast().add("value2");
		centroidList.getLast().add("8.5");
		centroidList.add(new LinkedList<>());
		centroidList.getLast().add("1");
		centroidList.getLast().add("value1");
		centroidList.getLast().add("5.0");
		try {
			assertEquals(centroidList, new QTMiner("test.dmp").getCentroidsList());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
