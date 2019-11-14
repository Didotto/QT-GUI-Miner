package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import javafx.scene.control.TableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;

import model.DataModel;
import view.AlertDialog;
import view.SaveView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
public class DataVisualizationController extends Controller {
	@FXML
	private TableView<List<String>> tableData;
	
	@FXML
	private Button saveButton;
	
	@FXML
	private Button plotButton;
	
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	public void init(DataModel model, Stage controlledStage) {
		super.init(model, controlledStage);
		input = model.getInputStream();
		output = model.getOutputStream();
		tableData.setRowFactory(tableView -> new TableRow<List<String>>() {
			@Override
			protected void updateItem(List<String> item, boolean empty) {
				super.updateItem(item, empty);
				if(item == null) {
					setGraphic(null);
				}else {
					int id = Integer.parseInt(item.get(0));
					double r = ((id+5)*23 * 0.1d) % 1.0d;
					double g = ((id+5)*23 * 0.2d) % 1.0d;
					double b = ((id+5)*23 * 0.3d) % 1.0d;
					Color background = Color.color(r, g, b);
					background = background.brighter().brighter();
					setStyle("-fx-background-color: #" + background.toString().substring(2, 8));
				}
				
			}
		});
		
		TableColumn<List<String>, String> column = new TableColumn<>("clusterId");
		column.setCellValueFactory(x -> new ReadOnlyObjectWrapper(x.getValue().get(0)));
		tableData.getColumns().add(column);
	}
	
	private void learningFromFile() throws SocketException, IOException, ClassNotFoundException, ServerException{
		output.writeObject(3);
		output.writeObject(model.getFileName());
		LinkedList<String> risultato_schema;
		LinkedList<LinkedList<String>> risultato_centroidi;
		String result = (String)input.readObject();
		if(result.contentEquals("OK")) {
			//System.out.println((String)input.readObject());
			risultato_schema=(LinkedList<String>)input.readObject();
			risultato_centroidi=(LinkedList<LinkedList<String>>)input.readObject();
		} else throw new ServerException(result);
		int i=0;
		ObservableList<List<String>> data = FXCollections.observableArrayList(risultato_centroidi);
		for(String attribute: risultato_schema) {
			final int  j = i;
			TableColumn<List<String>, String> column = new TableColumn<>(attribute);
			column.setCellValueFactory(x -> new ReadOnlyObjectWrapper(x.getValue().get(j)));
			tableData.getColumns().add(column);
			i++;
		}
		
		tableData.setItems(data);
	}
	
	private void learningFromDbTable() throws SocketException, IOException, ClassNotFoundException, ServerException{
		output.writeObject(1);
		output.writeObject(model.getRadius());
		LinkedList<String> risultato_schema;
		LinkedList<LinkedList<LinkedList<String>>> risultato_tabella;
		int numero_cluster;
		String result = (String)input.readObject();
		if(result.equals("OK")){
			//recv risultato schema
			risultato_schema=(LinkedList<String>)input.readObject();
			numero_cluster=(int)input.readObject();
			risultato_tabella=(LinkedList<LinkedList<LinkedList<String>>>)input.readObject();
			
		} else throw new ServerException(result);
		
		int i=0;
		for(String attribute: risultato_schema) {
			final int  j = i;
			TableColumn<List<String>, String> column = new TableColumn<>(attribute);
			column.setCellValueFactory(x -> new ReadOnlyObjectWrapper(x.getValue().get(j)));
			tableData.getColumns().add(column);
			i++;
		}
		
		ObservableList<List<String>> data = FXCollections.observableArrayList();
		for(List<LinkedList<String>> cluster: risultato_tabella) {
			for(List<String> tuple: cluster) {
				data.add(tuple);
			}
		}
		tableData.setItems(data);
	}
	
	private void storeTableFromDb() throws SocketException,ServerException,IOException,ClassNotFoundException{
		output.writeObject(0);
		output.writeObject(model.getTableName());
		String result = (String)input.readObject();
		if(!result.equals("OK"))
			throw new ServerException(result);
		
	}
	
	public void updateTable(boolean isLoadDB) {
		if(!isLoadDB) {
			try {
				learningFromFile();
			}catch(SocketException e) {
				System.out.println("[!]Error: " + e.getMessage());
			}catch(IOException e){
				System.out.println("[!]Error: " + e.getMessage());
			}catch(ClassNotFoundException e) {
				System.out.println("[!]Error: " + e.getMessage());
			}catch(ServerException e) {
				System.out.println("[!]Error: " + e.getMessage());
			}
		}else {
			try {
				storeTableFromDb();
				learningFromDbTable();
			}catch(SocketException e) {
				System.out.println("[!]Error: " + e.getMessage());
			}catch(IOException e){
				System.out.println("[!]Error: " + e.getMessage());
			}catch(ClassNotFoundException e) {
				System.out.println("[!]Error: " + e.getMessage());
			}catch(ServerException e) {
				System.out.println("[!]Error: " + e.getMessage());
			}
		}
	}
	
	public void saveClicked (MouseEvent e) {
		try {
			new SaveView(this.model);
			//controlledStage.close();
		}catch (IOException ex){
			System.out.println("Error: " + ex.getMessage());
		}
	}
	

	 public void plotClicked (MouseEvent e){
	  
	 }
	 
}
