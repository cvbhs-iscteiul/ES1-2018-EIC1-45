package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class SettingsMail {
	
	/**Scene that creates settings page for email
	 * @return email settings page for the settings window
	 * */
	public static Scene getScene() {
		BorderPane frame = new BorderPane();
		frame.setLeft(WindowSettings.getSettingsLeftButtons());
		GridPane paneSettings = new GridPane();
		paneSettings.setPadding(new Insets(10));
		paneSettings.setHgap(10);
		paneSettings.setVgap(10);
		
		Label labelEmail = new Label("Email: ");
		Label labelPassword = new Label("Password: ");
		TextField textEmail = new TextField();
		PasswordField textPassword = new PasswordField();
		Button buttonConfirm = new Button("Confirm");
//		buttonConfirm.setOnAction(e ->);
		
		GridPane.setConstraints(labelEmail, 0, 0);
		GridPane.setConstraints(labelPassword, 0, 1);
		GridPane.setConstraints(textEmail, 1, 0);
		GridPane.setConstraints(textPassword, 1, 1);
		GridPane.setConstraints(buttonConfirm, 1, 2);
		paneSettings.getChildren().add(labelEmail);
		paneSettings.getChildren().add(labelPassword);
		paneSettings.getChildren().add(textEmail);
		paneSettings.getChildren().add(textPassword);
		paneSettings.getChildren().add(buttonConfirm);
		
		
		frame.setCenter(paneSettings);
		
		return new Scene(frame);
	}

}
