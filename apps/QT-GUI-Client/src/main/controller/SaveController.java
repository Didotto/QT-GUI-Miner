package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.AlertDialog;
import javafx.scene.control.Alert.AlertType;
import model.DataModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class SaveController extends Controller{
	@FXML
	private Button saveButton;
	
	@FXML
	private Button resetButton;
	
	@FXML
	private TextField fileName;
	
	private ObjectInputStream input;
	private ObjectOutputStream output;

	public void init(DataModel model, Stage controlledStage) {
		super.init(model, controlledStage);
		input = model.getInputStream();
		output = model.getOutputStream();
		//Binding resetButton and saveButton
		BooleanBinding isfileNameEmpty = Bindings.isEmpty(fileName.textProperty());
		resetButton.disableProperty().bind(isfileNameEmpty);
		saveButton.disableProperty().bind(isfileNameEmpty);


	}
	
	
	public void resetClicked (MouseEvent e) {
		if (!fileName.getText().equals("")) {
			fileName.clear();
		}
	}
	
	public void saveClicked (MouseEvent ev) {
		try {
			storeClustersInFile();
			
		}catch(SocketException e) {
			System.out.println("[!]Error: " + e.getMessage());
		}catch(ServerException e) {
			System.out.println("[!]Error: " + e.getMessage());
		}catch (IOException e) {
			System.out.println("[!]Error: " + e.getMessage());
		}catch (ClassNotFoundException e) {
			System.out.println("[!]Error: " + e.getMessage());
		}catch (Exception e) {
			System.out.println("[!]Error: " + e.getMessage());
		}
		System.out.println("Sono andato oltre il try catch");
	}
	
	private void storeClustersInFile() throws SocketException,ServerException,IOException,ClassNotFoundException{
		output.writeObject(2);
		output.writeObject(fileName.getText());
			if(((String)input.readObject()).equals("OK")) {
				new AlertDialog(AlertType.INFORMATION,
						"SAVING DIALOG",
						"ALL RIGHT !",
						"Saving Successful !",
						"successful_Icon.png"
						);
				String result = (String)input.readObject();
				if(!result.equals("OK"))
					 throw new ServerException(result);
			}else {
				AlertDialog a= new AlertDialog(AlertType.CONFIRMATION,
						"SAVING DIALOG",
						"WE'RE ALMOST DONE !",
						"Do you want to overwrite the file ?"
						);
				Optional<ButtonType> result = a.showAndWait();
				if (result.get() == ButtonType.OK){
					output.writeObject("Y");
					new AlertDialog(AlertType.INFORMATION,
							"SAVING DIALOG",
							"ALL RIGHT !",
							"Saving Successful !",
							"successful_Icon.png"
							);
					String results = (String)input.readObject();
					if(!results.equals("OK"))
						 throw new ServerException(results);
	            }else {
	            	System.out.print("PRIMA DI N");
	            	output.writeObject("N");
	            	System.out.print("DOPO DI N");
	            }
			}
			
		//CHIUDO LA FINESTRA
		controlledStage.close();
		//CHIUDO LA FINESTRA
		System.out.print("DOPO LA CHIUSURA");
	}
	
}