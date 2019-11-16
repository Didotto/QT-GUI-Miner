package data;

import java.util.List;
import java.util.LinkedList;
import java.util.TreeSet;
import database.DbAccess;
import database.Example;
import database.DatabaseConnectionException;
import database.TableSchema;
import database.TableData;
import database.NoValueException;
import database.EmptySetException;
import database.QUERY_TYPE;
import java.sql.SQLException;
public class Data {
	
	private List<Example> data;
	private List<Attribute> attributeSet;
	
	
	public Data(String table) throws NoValueException, DatabaseConnectionException, SQLException, EmptyDatasetException {
		
		DbAccess dbaccess = new DbAccess();
		dbaccess.initConnection();
		
		attributeSet = new LinkedList<Attribute>();
		
		TableSchema tSchema = new TableSchema(dbaccess, table); 
		TableData tData = new TableData(dbaccess);
		
		//DA DECIDERE
		try {
			data = tData.getDistinctTransazioni(table);
		} catch(EmptySetException e) {
			throw new EmptyDatasetException();
		}
		
		for(int i = 0; i<tSchema.getNumberOfAttributes(); i++) {
			TableSchema.Column column = tSchema.getColumn(i);
			
			if(column.isNumber()) {
				attributeSet.add(new ContinuousAttribute(column.getColumnName(), i,
						(double) tData.getAggregateColumnValue(table, column, QUERY_TYPE.MIN),
						(double) tData.getAggregateColumnValue(table, column, QUERY_TYPE.MAX)
						));
			} else {
				attributeSet.add(new DiscreteAttribute(column.getColumnName(), i, 
						(TreeSet) tData.getDistinctColumnValues(table, column)
						));
			}
		}
		dbaccess.closeConnection();
		
		/*
		// TO DO : memorizzare le transazioni secondo lo schema della tabella nelle specifiche
		data[0][0] = "sunny"; data[0][1] = new Double(30.3); data[0][2] =  "high"; data[0][3] = "weak"; data[0][4] = "no";
		data[1][0] = "sunny"; data[1][1] = new Double(30.3); data[1][2] =  "high"; data[1][3] = "strong"; data[1][4] = "no";
		data[2][0] = "overcast"; data[2][1] = new Double(30); data[2][2] =  "high"; data[2][3] = "weak"; data[2][4] = "yes";
		data[3][0] = "rain"; data[3][1] = new Double(13); data[3][2] =  "high"; data[3][3] = "weak"; data[3][4] = "yes";
		data[4][0] = "rain"; data[4][1] = new Double(0); data[4][2] =  "normal"; data[4][3] = "weak"; data[4][4] = "yes";
		data[5][0] = "rain"; data[5][1] = new Double(0); data[5][2] =  "normal"; data[5][3] = "strong"; data[5][4] = "no";
		data[6][0] = "overcast"; data[6][1] = new Double(0.1); data[6][2] =  "normal"; data[6][3] = "strong"; data[6][4] = "yes";
		data[7][0] = "sunny"; data[7][1] = new Double(13); data[7][2] =  "high"; data[7][3] = "weak"; data[7][4] = "no";
		data[8][0] = "sunny"; data[8][1] = new Double(0.1); data[8][2] =  "normal"; data[8][3] = "weak"; data[8][4] = "yes";
		data[9][0] = "rain"; data[9][1] = new Double(12); data[9][2] =  "normal"; data[9][3] = "weak"; data[9][4] = "yes";
		data[10][0] = "sunny"; data[10][1] = new Double(12.5); data[10][2] =  "normal"; data[10][3] = "strong"; data[10][4] = "yes";
		data[11][0] = "overcast"; data[11][1] = new Double(12.5); data[11][2] =  "high"; data[11][3] = "strong"; data[11][4] = "yes";
		data[12][0] = "overcast"; data[12][1] = new Double(29.21); data[12][2] =  "normal"; data[12][3] = "weak"; data[12][4] = "yes";
		data[13][0] = "rain"; data[13][1] = new Double(12.5); data[13][2] =  "high"; data[13][3] = "strong"; data[13][4] = "no";
		
		// numberOfExamples
		
		numberOfExamples=14;		 
		
		
		//explanatory Set
		
		attributeSet = new LinkedList<Attribute>();

		// TO DO : avvalorare ciascune elemento di attributeSet con un oggetto della classe DiscreteAttribute che modella il corrispondente attributo (e.g. outlook, temperature,etc)
		// nel seguito si fornisce l'esempio per outlook
		
		attributeSet.add(new DiscreteAttribute("Outlook", 0, 
				new TreeSet<String>(Arrays.asList(new String[] {"overcast", "rain", "sunny"}))));
		
		attributeSet.add(new ContinuousAttribute("Temperature", 1, 0, 30.3));
		
		attributeSet.add(new DiscreteAttribute("Humidity", 2,
				new TreeSet<String>(Arrays.asList(new String[] {"high", "normal"}))));
		
		attributeSet.add(new DiscreteAttribute("Wind", 3,
				new TreeSet<String>(Arrays.asList(new String[] {"strong ", "weak"}))));
		
		attributeSet.add(new DiscreteAttribute("PlayTennis", 4,
				new TreeSet<String>(Arrays.asList(new String[] {"no", "yes"}))));
		
		if(numberOfExamples == 0)
			throw new EmptyDatasetException();
		*/
		
		
	}
	
	public int getNumberOfExamples(){
		return this.data.size();
	}
	
	public int getNumberOfAttributes(){
		return this.attributeSet.size();
	}
	
	public List<Attribute> getAttributeSchema() {
		return this.attributeSet;
	}
	
	public Object getAttributeValue(int exampleIndex, int attributeIndex){
		return this.data.get(exampleIndex).get(attributeIndex);
	}
	
	Attribute getAttribute(int index){
		return this.attributeSet.get(index);
	}
	
	
	public String toString(){
		String str = "";
		for(int i=0; i<this.getNumberOfAttributes(); i++) {
			str += this.getAttribute(i).toString() + ", ";
		}
		str += "\n";
		
		for(int i=0; i<this.getNumberOfExamples(); i++) {
			str += (i+1) + ": ";
			for(int j=0; j<this.getNumberOfAttributes(); j++) {
				str += this.getAttributeValue(i, j).toString() + ", ";
			}
			str += "\n";
		}
		
		return str;
		
	}

	public Tuple getItemSet(int index) {
		Tuple tuple = new Tuple(this.getNumberOfAttributes());
		for(int i = 0; i<this.getNumberOfAttributes(); i++)
			if(attributeSet.get(i) instanceof DiscreteAttribute) {
				tuple.add(new DiscreteItem((DiscreteAttribute)attributeSet.get(i), (String)this.getAttributeValue(index,i)), i);
			}else {
				tuple.add(new ContinuousItem((ContinuousAttribute)attributeSet.get(i), (Double)this.getAttributeValue(index,i)), i);
			}		
		return tuple;
	}
	
}
