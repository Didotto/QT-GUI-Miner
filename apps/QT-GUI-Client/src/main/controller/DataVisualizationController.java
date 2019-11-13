package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

import model.DataModel;
import view.AlertDialog;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.net.SocketException;
import java.util.LinkedList;
public class DataVisualizationController extends Controller {
	@FXML
	private TableView<String> tableData;
	
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
			}
		else throw new ServerException(result);
		System.out.println("Schema: " +risultato_schema);
		System.out.println("Centroidi: ");
		for (LinkedList<String> l: risultato_centroidi) {
			System.out.println(l);
		}
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
			
		}

		else throw new ServerException(result);
		
		System.out.println("Schema: " +risultato_schema);
		for (LinkedList<LinkedList<String>> i: risultato_tabella) {
			for (LinkedList<String> j: i) {
				System.out.println("TUPLA: " +j);
			}
		}
		
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
}
