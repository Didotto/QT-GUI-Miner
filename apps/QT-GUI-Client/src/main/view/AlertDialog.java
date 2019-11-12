package view;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
public class AlertDialog extends Alert {	
	
	private final static String IMAGE_FOLDER = "file:src/main/resources/";
	
	private final static String WARNING_IMAGE = "warning_icon.png";
	
	private final static String ERROR_IMAGE = "error_icon.png";
	
	public AlertDialog (Alert.AlertType type, String title, String header, String content, String img) {
		super(type);
		this.setTitle(title);
		this.setHeaderText(header);
		this.setContentText(content);
		Image image = new Image(IMAGE_FOLDER + img);
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(64);
		imageView.setFitHeight(64);
		this.setGraphic(imageView);
		Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
		stage.getIcons().add(image);
		this.showAndWait();
	}
	
	public AlertDialog (Alert.AlertType type, String title, String header, String content) {
		super(type);
		this.setTitle(title);
		this.setHeaderText(header);
		this.setContentText(content);
		Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
		if(type == Alert.AlertType.WARNING) {
			stage.getIcons().add(new Image(IMAGE_FOLDER + WARNING_IMAGE));
		} else {
			stage.getIcons().add(new Image(IMAGE_FOLDER + ERROR_IMAGE));
		}
		this.showAndWait();
	}
}
