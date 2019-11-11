package controller;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.Bindings;


public class GearController extends Controller{
	@FXML
	private AnchorPane ConnectionPane;
	@FXML
	private TextField ipAddress;
	@FXML
	private TextField port;
	
	@FXML
	private Button connectionButton;
	
	@FXML
	public void initialize() {
		BooleanBinding isIpAddressFieldEmpty = Bindings.isEmpty(ipAddress.textProperty());
		BooleanBinding isPortFieldEmpty = Bindings.isEmpty(port.textProperty());
		connectionButton.disableProperty().bind(isIpAddressFieldEmpty.or(isPortFieldEmpty));
	}
	
	public void connectionClicked (MouseEvent event) {
		String ipAddr = ipAddress.getText();
		//TO-DO if ipAddr è regolare
		int port_conn = new Integer(port.getText()).intValue();
		try {
			model.connect(ipAddr, port_conn);
			controlledStage.close();
			System.out.println("Connessione AVVENUTA");
		} catch (IOException e) {
			//ALERT - Connessione non avvenuta
			System.out.println("Connessione non avvenuta");
		}
		
	}
}
