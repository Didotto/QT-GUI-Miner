package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;



import database.TableSchema.Column;

/**
 * This class model the entire transaction stored in a db
 *
 */
public class TableData {

	DbAccess db;
	
	/**
	 * Builds an object of class "TableData"
	 *@param db object that rappresent the information about connection to db and the connection itself
	 */
	public TableData(DbAccess db) {
		this.db = db;
	}

	/**
	 * Construct a query for db and save all transactions contained in db
	 *@param table the name of table
	 *@return a linked list of example
	 *@throws SQLException if there are some problems with the connetion to db
	 *@throws EmptySetException if there are no transactions retrived
	 */
	
	public List<Example> getDistinctTransazioni(String table) throws SQLException, EmptySetException{
		LinkedList<Example> transSet = new LinkedList<Example>();
		Statement statement;
		TableSchema tSchema = new TableSchema(db,table);
		
		
		String query="select distinct ";
		
		if(tSchema.getNumberOfAttributes()==0)
			throw new SQLException();
		
		for(int i=0;i<tSchema.getNumberOfAttributes();i++){
			Column c = tSchema.getColumn(i);
			if(i>0)
				query+=",";
			query += c.getColumnName();
		}
		
		query += (" FROM " + table);
		
		statement = db.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(query);
		boolean empty = true;
		while (rs.next()) {
			empty = false;
			Example currentTuple = new Example();
			for(int i = 0; i<tSchema.getNumberOfAttributes(); i++)
				if(tSchema.getColumn(i).isNumber())
					currentTuple.add(rs.getDouble(i+1));
				else
					currentTuple.add(rs.getString(i+1));
			transSet.add(currentTuple);
		}
		rs.close();
		statement.close();
		if(empty)
			throw new EmptySetException();
		
		
		return transSet;

	}
	
	/**
	 * Construct a query for db and save all transactions contained in db
	 *@param table the name of table
	 *@param column name of the column in the table 
	 *@return  a set of ordered distinct values in the column of table
	 *@throws SQLException if there are some problems with the connetion to db
	 */
	
	public  Set<Object>getDistinctColumnValues(String table,Column column) throws SQLException{
		Set<Object> valueSet = new TreeSet<Object>();
		Statement statement;
		//TableSchema tSchema = new TableSchema(db,table);
		
		
		String query="select distinct ";
		
		query += column.getColumnName();
		
		query += (" FROM "+table);
		
		query += (" ORDER BY " +column.getColumnName());
		
		
		
		statement = db.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
				if(column.isNumber())
					valueSet.add(rs.getDouble(1));
				else
					valueSet.add(rs.getString(1));
		}
		rs.close();
		statement.close();
		
		return valueSet;

	}

	/**
	 * Construct a query for db and save all transactions contained in db
	 *@param table the name of table
	 *@param column name of the column in the table 
	 *@param aggregate the aggregate operator for query
	 *@return min or max that dipends from query type
	 *@throws SQLException if there are some problems with the connetion to db
	 *@throws NoValueException if there is no min or max in the column
	 */
	
	public Object getAggregateColumnValue(String table,Column column,QUERY_TYPE aggregate) throws SQLException,NoValueException {
		Statement statement;
		//TableSchema tSchema=new TableSchema(db,table);
		Object value = null;
		String aggregateOp = "";
		
		String query = "select ";
		if(aggregate==QUERY_TYPE.MAX)
			aggregateOp += "max";
		else
			aggregateOp += "min";
		query += aggregateOp + "(" + column.getColumnName() + ") FROM " + table;
		
		
		statement = db.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(query);
		if (rs.next()) {
				if(column.isNumber())
					value=rs.getDouble(1);
				else
					value=rs.getString(1);
			
		}
		rs.close();
		statement.close();
		if(value==null)
			throw new NoValueException("No " + aggregateOp+ " on " + column.getColumnName());
			
		return value;

	}

	

	

}
