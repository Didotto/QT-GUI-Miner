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
		String result = (String)input.readObject();
		if(result.contentEquals("OK"))
			System.out.println((String)input.readObject());
		else throw new ServerException(result);
		
	}
	
	private void learningFromDbTable() throws SocketException, IOException, ClassNotFoundException, ServerException{
		output.writeObject(1);
		output.writeObject(model.getRadius());
		String result = (String)input.readObject();
		if(result.equals("OK")){
			System.out.println("Number of Clusters:" + input.readObject());
			System.out.println((String)input.readObject());
		}
		else throw new ServerException(result);
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
