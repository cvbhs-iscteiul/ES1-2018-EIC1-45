package gui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow extends Application{
	
	Stage mainWindow;
	Scene sceneDefault, sceneFB, sceneMail, sceneTwitter;
	Button buttonFB, buttonMail, buttonTwitter;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainWindow = primaryStage;
		mainWindow.setTitle("Bom Dia Academia");
		
		buttonFB = new Button("FaceBook");
		buttonFB.setOnAction(e -> System.out.println("asdf"));
		
		StackPane layout = new StackPane();
		layout.getChildren().add(buttonFB);
		
		sceneDefault = new Scene(layout, 1000, 600);
		primaryStage.setScene(sceneDefault);
		primaryStage.show();
	}

}


