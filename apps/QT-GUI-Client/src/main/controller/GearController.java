package controller;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.Bindings;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
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
	
	private boolean re_ip(final String ip) {
	    String pattern = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			     "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			     "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			     "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	    return ip.matches(pattern);
	}
	
	
	public void connectionClicked (MouseEvent event) {
		String ipAddr = ipAddress.getText();
		int port_conn = new Integer(port.getText()).intValue();
		if (re_ip(ipAddr)){
				if (port_conn>=1 && port_conn<=65535){
					try {
						model.connect(ipAddr, port_conn);
						controlledStage.close();
						System.out.println("Connessione AVVENUTA");
					} catch (IOException e) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("CONNECTION FAILED");
						alert.setHeaderText("CONNECTION PROBLEMS");
						alert.setContentText("There is a problem with the server... Try to replace IP or Port!");
						Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
						stage.getIcons().add(new Image("file:src/main/resources/connection_failed.png"));
						alert.showAndWait();
						System.out.println("Connessione non avvenuta");
					}
				}else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("CONNECTION FAILED");
					alert.setHeaderText("CONNECTION PROBLEMS");
					alert.setContentText("The Port doesn't respect the standards...Try again!");
					Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
					stage.getIcons().add(new Image("file:src/main/resources/connection_failed.png"));
					alert.showAndWait();
				}
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("CONNECTION FAILED");
				alert.setHeaderText("CONNECTION PROBLEMS");
				alert.setContentText("The IP Address doesn't respect the standards...Try again!");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("file:src/main/resources/connection_failed.png"));
				alert.showAndWait();
			}
	}
	
}
