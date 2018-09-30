package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {

	Stage windowMain;
	Scene sceneDefault, sceneMail, sceneFB, sceneTwitter;

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
		sceneMakeMail();

		// Stage Show
		windowMain.setScene(sceneDefault);
		windowMain.show();
	}

	private void sceneMakeMail() {
		BorderPane frame = new BorderPane();
		frame.setTop(buttonsTop());

		
		//body of email and options
		VBox optionsAndContent = new VBox(5);
		//body of email
		TextArea bodyEmail = new TextArea(); 
		bodyEmail.setPrefHeight(550);	
		
		//left list of emails
		ListView<String> listEmails = new ListView<>();
		setEmailList(listEmails, bodyEmail);
		frame.setLeft(listEmails);
		
		optionsAndContent.getChildren().addAll(buttonsOptionsEmail(), bodyEmail);
		frame.setCenter(optionsAndContent);
		
		sceneMail = new Scene(frame, 1000, 600);
	}

	private void setEmailList(ListView<String> listEmails, TextArea bodyEmail) {
//		listEmails.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); //Não é necessário
		listEmails.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
			bodyEmail.setText("aqui deve estar o email com o indice " + listEmails.getSelectionModel().getSelectedIndex()); //função para ir buscar o texto do email aqui
		});
		//Função da class Mail que devolve um ArrayList com os títulos dos emails
		ArrayList<String> arrayListEmails = new ArrayList<>();//Mail.getEmailList(); 
		//temp enquanto nao ha funçao do mail
		for (int i = 0; i < 26; i++) {
			arrayListEmails.add("Email " + i);
		}
		for (String email : arrayListEmails) {
			listEmails.getItems().add(email);
		}
	}

	private HBox buttonsOptionsEmail() {
		HBox options = new HBox(10);
		options.setAlignment(Pos.TOP_CENTER);
		
		Button buttonReply = new Button("Reply");
//		buttonDefault.setOnAction(e -> ); //reply tem que dar erro caso o user nao tenho ainda selecionado nenhum email
		
		//refresh?
		
		options.getChildren().add(buttonReply);
		return options;
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

		sceneDefault = new Scene(frame, 1000, 600);
	}

	private HBox buttonsTop() {
		Button buttonDefault = new Button("Home");
		buttonDefault.setOnAction(e -> windowMain.setScene(sceneDefault));

		Button buttonMail = new Button("Mail");
		buttonMail.setOnAction(e -> windowMain.setScene(sceneMail));

		Button buttonFB = new Button("FaceBook");
		buttonFB.setOnAction(e -> windowMain.setScene(sceneFB));

		Button buttonTwitter = new Button("Twitter");
		buttonTwitter.setOnAction(e -> windowMain.setScene(sceneTwitter));

		Button buttonSettings = new Button("Settings");
		buttonSettings.setOnAction(e -> SettingsWindow.openSettings());
		
		HBox buttonsTop = new HBox(10);
		buttonsTop.getChildren().add(buttonDefault);
		buttonsTop.getChildren().add(buttonMail);
		buttonsTop.getChildren().add(buttonFB);
		buttonsTop.getChildren().add(buttonTwitter);
		buttonsTop.getChildren().add(buttonSettings);
		buttonsTop.setAlignment(Pos.CENTER);
		buttonsTop.setPadding(new Insets(10));;
		return buttonsTop;
	}

}
