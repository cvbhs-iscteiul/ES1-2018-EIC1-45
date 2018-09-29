package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsWindow {
	
	
	static Scene sceneMailSettings, sceneFBSettings, sceneTwitterSettings;
	
	public static void openSettings() {
		//Stage
		Stage windowSettings = new Stage();
		windowSettings.setTitle("Settings");
		windowSettings.initModality(Modality.APPLICATION_MODAL);
		windowSettings.setMinHeight(400);
		windowSettings.setMinWidth(800);
		
		//Buttons
		Button  buttonMailSettings = new Button("Mail");
		buttonMailSettings.setOnAction(e -> windowSettings.setScene(sceneMailSettings));
		
		Button buttonFBSettings = new Button("FaceBook");
		buttonFBSettings.setOnAction(e -> windowSettings.setScene(sceneFBSettings));
		
		Button buttonTwitterSettings = new Button("Twitter");
		buttonTwitterSettings.setOnAction(e -> windowSettings.setScene(sceneTwitterSettings));
		
		//Scenes
		//Mail Settings
		VBox layout = new VBox(10);
		layout.getChildren().add(buttonMailSettings);
		layout.getChildren().add(buttonFBSettings);
		layout.getChildren().add(buttonTwitterSettings);
		layout.setAlignment(Pos.CENTER_LEFT);
		sceneMailSettings = new Scene(layout);
		
		//Stage Show
		windowSettings.setScene(sceneMailSettings);
		windowSettings.showAndWait();
		
	}

}
