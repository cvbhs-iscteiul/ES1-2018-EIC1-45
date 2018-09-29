package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {

	Stage windowMain;
	Scene sceneDefault, sceneMail, sceneFB, sceneTwitter;
	Button buttonDefault, buttonFB, buttonMail, buttonTwitter, buttonSettings;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Stage
		windowMain = primaryStage;
		windowMain.setTitle("Bom Dia Academia");

		// Buttons
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

		// Scenes
		sceneGetDefault();

		// Stage Show
		windowMain.setScene(sceneDefault);
		windowMain.show();
	}

	private void sceneGetDefault() {
		BorderPane frame = new BorderPane();
		HBox buttonsTop = buttonsTop();
		frame.setTop(buttonsTop);

		try {
			Image imageIscte = new Image(new FileInputStream("src/gui/iscte.png"));
			ImageView imageViewIscte = new ImageView(imageIscte);
			frame.setCenter(imageViewIscte);
		} catch (FileNotFoundException e) {
			System.out.println("Erro no path da Imagem ISCTE");
		}

		sceneDefault = new Scene(frame, 1000, 600);
	}

	private HBox buttonsTop() {
		HBox buttonsTop = new HBox(10);
		buttonsTop.getChildren().add(buttonDefault);
		buttonsTop.getChildren().add(buttonFB);
		buttonsTop.getChildren().add(buttonMail);
		buttonsTop.getChildren().add(buttonTwitter);
		buttonsTop.getChildren().add(buttonSettings);
		buttonsTop.setAlignment(Pos.CENTER);
		return buttonsTop;
	}

}
