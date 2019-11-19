package controller;

import javafx.fxml.FXML;
import javafx.stage.WindowEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;

import javafx.scene.control.TableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;

import model.DataModel;
import view.AlertDialog;
import view.PieChartView;
import view.SaveView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Label;


public class DataVisualizationController extends ColoredController {
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
					setStyle("-fx-background-color: #" +
							generatePastelColor(Integer.parseInt(item.get(0))).toString().substring(2, 8));
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
			new AlertDialog(AlertType.WARNING,
					"WARNING",
					"COMMINICATION ERROR",
					e.getMessage()
					);
		}
	}
	
	private void learningFromFile() throws SocketException, IOException, ClassNotFoundException, ServerException{
		output.writeObject(3);
		output.writeObject(model.getFileData().getFileName()+".dmp");
		String result = (String)input.readObject();
		if(result.contentEquals("OK")) {
			model.getFileData().setScheme((LinkedList<String>)input.readObject());
			model.getFileData().setData((LinkedList<LinkedList<String>>)input.readObject());
			model.getFileData().setRadius((Double)input.readObject());
		} else throw new ServerException(result);
		int i=0;
		ObservableList<List<String>> data = FXCollections.observableArrayList(model.getFileData().getData());
		for(String attribute: model.getFileData().getScheme()) {
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
		output.writeObject(model.getDatabaseData().getRadius());
		String result = (String)input.readObject();
		if(result.equals("OK")){
			//recv risultato schema
			model.getDatabaseData().setScheme((LinkedList<String>)input.readObject());
			model.getDatabaseData().setData((LinkedList<LinkedList<LinkedList<String>>>)input.readObject());
			
		} else throw new ServerException(result);
		
		int i=0;
		for(String attribute: model.getDatabaseData().getScheme()) {
			final int  j = i;
			TableColumn<List<String>, String> column = new TableColumn<>(attribute);
			column.setCellValueFactory(x -> new ReadOnlyObjectWrapper(x.getValue().get(j)));
			tableData.getColumns().add(column);
			i++;
		}
		
		ObservableList<List<String>> data = FXCollections.observableArrayList();
		for(List<LinkedList<String>> cluster: model.getDatabaseData().getData()) {
			for(List<String> tuple: cluster) {
				data.add(tuple);
			}
		}
		tableData.setItems(data);
	}
	
	private void storeTableFromDb() throws SocketException,ServerException,IOException,ClassNotFoundException{
		output.writeObject(0);
		output.writeObject(model.getDatabaseData().getDatabaseTable());
		String result = (String)input.readObject();
		if(!result.equals("OK"))
			throw new ServerException(result);
		
	}
	
	public void updateTable (boolean isLoadDB) throws ServerException, IOException, SocketException, ClassNotFoundException{
		if(!isLoadDB) {
			learningFromFile();
			radiusLabel.setText("Radius : " + model.getFileData().getRadius());
		}else {
			storeTableFromDb();
			learningFromDbTable();
		}
	}
	
	public void saveClicked (MouseEvent e) {
		try {
			new SaveView(this.model);
		}catch (IOException ex){
			new AlertDialog(AlertType.WARNING,
					"ERROR",
					"ERROR OPENING A NEW WINDOW",
					"Unable to open a new saving window. Try again...!"
					);
		}
	}
	

	 public void plotClicked (MouseEvent e){
		 try {
			 new PieChartView(this.model);
		 } catch (IOException ex) {
			 new AlertDialog(AlertType.WARNING,
						"ERROR",
						"ERROR OPENING A NEW WINDOW",
						"Unable to open a new chart window. Try again...!"
						);
		 }
	 }
	 
}
