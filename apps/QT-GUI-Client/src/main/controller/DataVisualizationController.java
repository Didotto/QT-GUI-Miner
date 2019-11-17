package controller;

import javafx.fxml.FXML;
import javafx.stage.WindowEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;

import javafx.scene.control.TableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;

import model.DataModel;
import view.PieChartView;
import view.SaveView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Label;


public class DataVisualizationController extends Controller {
	@FXML
	private TableView<List<String>> tableData;
	
	@FXML
	private Button saveButton;
	
	@FXML
	private Button plotButton;
	@FXML
	private Label radiusLabel;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private Double radiusFile;
	private LinkedList<String> scheme;
	private LinkedList<LinkedList<String>> centroidData;
	private LinkedList<LinkedList<LinkedList<String>>> completeData;
	
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
					Color background = Color.hsb(
							(id * 67.0d) % 360.0d,
							(0.3d + id*0.01) %1.0d,
							0.8d);
					setStyle("-fx-background-color: #" + background.toString().substring(2, 8));
				}
				
			}
		});
		controlledStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent window)
            {
                closeWindow(window);
            }
        });
	}
	
	public void closeWindow(WindowEvent window) {
		try {
			output.writeObject(-1);
		}catch (IOException e) {
			System.out.println("[!]Error: " + e.getMessage());
		}
	}
	
	private void learningFromFile() throws SocketException, IOException, ClassNotFoundException, ServerException{
		output.writeObject(3);
		output.writeObject(model.getFileName()+".dmp");
		String result = (String)input.readObject();
		if(result.contentEquals("OK")) {
			scheme = (LinkedList<String>)input.readObject();
			centroidData = (LinkedList<LinkedList<String>>)input.readObject();
		} else throw new ServerException(result);
		int i=0;
		ObservableList<List<String>> data = FXCollections.observableArrayList(centroidData);
		for(String attribute: scheme) {
			final int  j = i;
			TableColumn<List<String>, String> column = new TableColumn<>(attribute);
			column.setCellValueFactory(x -> new ReadOnlyObjectWrapper(x.getValue().get(j)));
			tableData.getColumns().add(column);
			i++;
		}
		radiusFile = (Double)input.readObject();
		tableData.setItems(data);
	}
	
	private void learningFromDbTable() throws SocketException, IOException, ClassNotFoundException, ServerException{
		output.writeObject(1);
		output.writeObject(model.getRadius());
		LinkedList<String> risultato_schema;
		String result = (String)input.readObject();
		if(result.equals("OK")){
			//recv risultato schema
			risultato_schema = (LinkedList<String>)input.readObject();
			completeData = (LinkedList<LinkedList<LinkedList<String>>>)input.readObject();
			
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
		for(List<LinkedList<String>> cluster: completeData) {
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
	
	public void updateTable (boolean isLoadDB) throws ServerException, IOException, SocketException, ClassNotFoundException{
		if(!isLoadDB) {
			learningFromFile();
			radiusLabel.setText("Radius : " + radiusFile);
		}else {
			storeTableFromDb();
			learningFromDbTable();
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
		 try {
			 new PieChartView(this.model, completeData);
		 } catch (IOException ex) {
			 System.out.println("Error: " + ex.getMessage());
		 }
	 }
	 
}
