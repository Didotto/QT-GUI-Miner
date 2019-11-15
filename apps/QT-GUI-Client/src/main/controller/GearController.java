package controller;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DataModel;
import javafx.scene.control.Button;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.binding.Bindings;

import view.AlertDialog;
import javafx.scene.control.Alert.AlertType;


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
	private Button resetButton;
	
	private BooleanBinding isIpAddressFieldEmpty;
	private BooleanBinding isPortFieldEmpty;
	@FXML
	public void initialize() {
		
		isIpAddressFieldEmpty = Bindings.isEmpty(ipAddress.textProperty());
		isPortFieldEmpty = Bindings.isEmpty(port.textProperty());
		
	}
	@Override
	public void init (DataModel model, Stage controlledStage) {
		super.init(model, controlledStage);
		if (!model.isConnected()) {
			connectionButton.disableProperty().bind(isIpAddressFieldEmpty.or(isPortFieldEmpty));
			resetButton.disableProperty().bind(isIpAddressFieldEmpty.and(isPortFieldEmpty));
		}else {
			connectionButton.setText("Disconnect");
			ipAddress.setDisable(true);
			port.setDisable(true);
			resetButton.setDisable(true);
			
		}
	}
	private boolean re_ip(final String ip) {
	    String pattern = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			     "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			     "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			     "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	    return ip.matches(pattern);
	}
	
	public void resetClicked (MouseEvent event) {
		ipAddress.clear();
		port.clear();
	}
	
	public void connectionClicked (MouseEvent event) {
		if (connectionButton.getText().equals("Connect")) {
			String ipAddr = ipAddress.getText();
			int port_conn = new Integer(port.getText()).intValue();
			if (re_ip(ipAddr)){
					if (port_conn>=1 && port_conn<=65535){
						try {
							model.connect(ipAddr, port_conn);
							controlledStage.close();
							System.out.println("Connessione AVVENUTA");
						} catch (IOException e) {
							new AlertDialog(AlertType.ERROR,
									"CONNECTION FAILED",
									"CONNECTION PROBLEMS",
									"There is a problem with the server... Try to replace IP or Port!",
									"connection_failed.png"
									);
						}
					}else {
						new AlertDialog(AlertType.WARNING,
								"CONNECTION FAILED",
								"CONNECTION PROBLEMS",
								"The Port doesn't respect the standards...Try again!"
								);
					}
				}else {
					new AlertDialog(AlertType.ERROR,
							"CONNECTION FAILED",
							"CONNECTION PROBLEMS",
							"The IP Address doesn't respect the standards...Try again!"
							);
				}
		}else {
			try {
			model.disconnect();
			controlledStage.close();
			}catch (IOException e){
				
			}
		}
	}
	
}
