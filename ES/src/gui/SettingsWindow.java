package gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsWindow {
	
	
	static private Scene sceneMailSettings/*, sceneFBSettings, sceneTwitterSettings*/;
	static private int HEIGHT = 400, WIDTH = 800;
	
	public static void openSettings() {
		//Stage
		Stage windowSettings = new Stage();
		windowSettings.setTitle("Settings");
		windowSettings.initModality(Modality.APPLICATION_MODAL);

		sceneMailSettings = SettingsMail.getScene();
//		sceneFBSettings = sceneFBSettings.getScene;
//		sceneTwitterSettings = sceneTwitterSettings.getScene;
		
		//Stage Show
		windowSettings.setScene(sceneMailSettings); //default
		windowSettings.showAndWait();
	}
	
	public Node getSettingsLeftButtons() {
		VBox settingsLeftButtons = new VBox(10);
		
		
		
		return settingsLeftButtons;
	}

}
