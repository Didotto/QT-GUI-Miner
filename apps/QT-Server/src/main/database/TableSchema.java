package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * This class model the schema o table's db
 */

public class TableSchema {
	DbAccess db;
	/**
	 * This class model the column of table's db
	 */
	
	public class Column{
		private String name;
		private String type;
		/**
		 * Biulds an object of class Column
		 * @param name the name of Column
		 * @param type the type of column
		 */
		Column(String name,String type){
			this.name=name;
			this.type=type;
		}
		
		/**
		 * Returns the column name
		 * @return the column name
		 */
		
		public String getColumnName(){
			return name;
		}
		
		/**
		 * Returns true if the type of column is number, otherwise false
		 * @return true if the type of column is number, otherwise false
		 */
		
		public boolean isNumber(){
			return type.equals("number");
		}
		
		/**
		 * Returns the name and type of column as string
		 * @return the name and type of column as string
		 */
		public String toString(){
			return name+":"+type;
		}
	}
	List<Column> tableSchema=new ArrayList<Column>();
	
	/**
	 * Builds an object of class TableSchema
	 * @param db rappresent the connection with db
	 * @param tableName name of the table whose schema is to be saved
	 * @throws SQLException if there are some problems with the connection to db and with the access to table
	 */
	public TableSchema(DbAccess db, String tableName) throws SQLException{
		this.db=db;
		HashMap<String,String> mapSQL_JAVATypes=new HashMap<String, String>();
		//http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html
		mapSQL_JAVATypes.put("CHAR","string");
		mapSQL_JAVATypes.put("VARCHAR","string");
		mapSQL_JAVATypes.put("LONGVARCHAR","string");
		mapSQL_JAVATypes.put("BIT","string");
		mapSQL_JAVATypes.put("SHORT","number");
		mapSQL_JAVATypes.put("INT","number");
		mapSQL_JAVATypes.put("LONG","number");
		mapSQL_JAVATypes.put("FLOAT","number");
		mapSQL_JAVATypes.put("DOUBLE","number");
		
		
	
		 Connection con=db.getConnection();
		 DatabaseMetaData meta = con.getMetaData();
	     ResultSet res = meta.getColumns(null, null, tableName, null);
		   
	     while (res.next()) {
	         
	         if(mapSQL_JAVATypes.containsKey(res.getString("TYPE_NAME")))
	        		 tableSchema.add(new Column(
	        				 res.getString("COLUMN_NAME"),
	        				 mapSQL_JAVATypes.get(res.getString("TYPE_NAME")))
	        				 );
	
	         
	         
	      }
	      res.close();
	
	
	    
	    }
	  
		/**
		 * Returns the number of attributes (size of table schema)
		 * @return the size of table schema
		 */
		public int getNumberOfAttributes(){
			return tableSchema.size();
		}
		
		/**
		 * Returns the column corresponding to index specified
		 * @param index index corresponding to column of which you are interested
		 * @return the column corresponding to index specified
		 */
		
		public Column getColumn(int index){
			return tableSchema.get(index);
		}

		
	}

		     


