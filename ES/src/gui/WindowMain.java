package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class WindowMain extends Application {

	private static Stage windowMain;
	private static Scene sceneDefault, sceneMail/*, sceneFB, sceneTwitter*/;
	public static int  HEIGHT = 600, WIDTH = 1000;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Stage
		windowMain = primaryStage;
		windowMain.setTitle("Bom Dia Academia");

		// Scenes
		sceneMakeDefault();
		sceneMail = SceneMail.getSceneMail();

		// Stage Show
		windowMain.setScene(sceneDefault);
		windowMain.show();
	}

	private void sceneMakeDefault() {
		BorderPane frame = new BorderPane();
		frame.setTop(buttonsTop());

		try {
			Image imageIscte = new Image(new FileInputStream("src/gui/iscte.png"));
			ImageView imageViewIscte = new ImageView(imageIscte);
			frame.setCenter(imageViewIscte);
		} catch (FileNotFoundException e) {
			System.out.println("Erro no path da Imagem ISCTE");
		}

		sceneDefault = new Scene(frame, WIDTH, HEIGHT);
	}

	static Node buttonsTop() {
		Button buttonMail = new Button("Mail");
		buttonMail.setOnAction(e -> windowMain.setScene(sceneMail));

		Button buttonFB = new Button("FaceBook");
//		buttonFB.setOnAction(e -> windowMain.setScene(sceneFB));

		Button buttonTwitter = new Button("Twitter");
//		buttonTwitter.setOnAction(e -> windowMain.setScene(sceneTwitter));

		Button buttonSettings = new Button("Settings");
		buttonSettings.setOnAction(e -> SettingsWindow.openSettings());
		
		BorderPane buttonsTop = new BorderPane();
		buttonsTop.setPadding(new Insets(10));
		HBox buttonsTopMiddle = new HBox(10);
		buttonsTopMiddle.setAlignment(Pos.CENTER);
		buttonsTopMiddle.getChildren().add(buttonMail);
		buttonsTopMiddle.getChildren().add(buttonFB);
		buttonsTopMiddle.getChildren().add(buttonTwitter);
		Region padderRegion = new Region();
		padderRegion.prefWidthProperty().bind(buttonSettings.widthProperty());
		buttonsTop.setLeft(padderRegion);
		buttonsTop.setCenter(buttonsTopMiddle);
		buttonsTop.setRight(buttonSettings);
		return buttonsTop;
	}

	public static double getDefaultHeight(){
		return sceneDefault.heightProperty().get();
	}
	
	public static double getDefaultWidth(){
		return sceneDefault.widthProperty().get();
	}
	
}
