package gui;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainWindow extends Application{
	
	Stage windowMain;
	Scene sceneDefault, sceneMail, sceneFB, sceneTwitter;
	Button buttonDefault, buttonFB, buttonMail, buttonTwitter, buttonSettings;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		windowMain = primaryStage;
		windowMain.setTitle("Bom Dia Academia");

		//Botões
		buttonDefault = new Button("Home");
		buttonDefault.setOnAction(e -> windowMain.setScene(sceneDefault));
		
		buttonMail = new Button("Mail");
		buttonMail.setOnAction(e -> windowMain.setScene(sceneMail));
		
		buttonFB = new Button("FaceBook");
		buttonFB.setOnAction(e -> windowMain.setScene(sceneFB));
		
		buttonTwitter = new Button("Twitter");
		buttonTwitter.setOnAction(e -> windowMain.setScene(sceneTwitter));
		
		buttonSettings = new Button("Settings");
		buttonSettings.setOnAction(e -> SettingsWindow.openSettings());
		
		
		
		HBox layout = new HBox(10);
		layout.getChildren().add(buttonDefault);
		layout.getChildren().add(buttonFB);
		layout.getChildren().add(buttonMail);
		layout.getChildren().add(buttonTwitter);
		layout.getChildren().add(buttonSettings);
		layout.setAlignment(Pos.TOP_CENTER);
		
		sceneDefault = new Scene(layout, 1000, 600);
		windowMain.setScene(sceneDefault);
		windowMain.show();
	}

}


