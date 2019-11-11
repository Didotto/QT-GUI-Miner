package controller;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

import javafx.beans.binding.*;



public class GearController {
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
		int port_conn = new Integer(port.getText()).intValue();
		
		if (ipAddr.equals("127.0.0.1") && port_conn==8080) {
			//non faccio la connessione qui ma tipo "invio el stringhe chiamando un altro metodo"
			// se non riesce a connettersi non la chiudo
	}else {
		// dati immessi non corretti : non si chiude
	}
}
}