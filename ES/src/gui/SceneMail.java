package gui;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SceneMail {

	public static Scene getSceneMail() {
		BorderPane frame = new BorderPane();
		frame.setTop(WindowMain.buttonsTop());

		
		//body of email and options
		VBox optionsAndContent = new VBox(10);
		//body of email
		TextArea bodyEmail = new TextArea(); 
		bodyEmail.prefWidthProperty().bind(optionsAndContent.widthProperty());
		bodyEmail.prefHeightProperty().bind(optionsAndContent.heightProperty());
		bodyEmail.setEditable(false);
		bodyEmail.setWrapText(true);
		bodyEmail.setPadding(new Insets(10, 0, 0, 10));
		
		//left list of emails 
		ListView<String> listEmails = new ListView<>();
		setEmailList(listEmails, bodyEmail);
		frame.setLeft(listEmails);
		
		optionsAndContent.getChildren().addAll(buttonsOptionsEmail(listEmails, bodyEmail), bodyEmail);
		frame.setCenter(optionsAndContent);
		
		return new Scene(frame, WindowMain.getDefaultWidth(), WindowMain.getDefaultHeight());
		
	}
	
	private static void setEmailList(ListView<String> listEmails, TextArea bodyEmail) {
//		if() //user not authenticated
//			return;
		
		if (listEmails.getItems() != null)
			listEmails.getItems().clear();
		listEmails.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
			bodyEmail.setText(funcGetTextMail(listEmails.getSelectionModel().getSelectedIndex())); //função para ir buscar o texto do email aqui
		});
		//Função da class Mail que devolve um ArrayList com os títulos dos emails
		ArrayList<String> arrayListEmails = new ArrayList<>();//Mail.getEmailList(); 
		//temp enquanto nao ha funçao do mail
		for (int i = 0; i < 26; i++) {
			arrayListEmails.add("Email " + i );
		}
		for (String email : arrayListEmails) {
			listEmails.getItems().add(email);
		}listEmails.getSelectionModel().select(0);
	}
	

	private static String funcGetTextMail(int selectedIndex) { // funcao para imitar a que se vai usar da classe do email
		String body = "";
		for (int i = 0; i < 36; i++) {
			body += "corpo do email" + selectedIndex + " ";
		}
		return body;
	}

	private static HBox buttonsOptionsEmail(ListView<String> listEmails, TextArea bodyEmail) {
		HBox optionsEmail = new HBox(10);
		optionsEmail.setPadding(new Insets(0, 0, 0, 10));
		optionsEmail.setAlignment(Pos.BASELINE_LEFT);
		
		Button buttonReply = new Button("Reply");
//		buttonReply.setOnAction(e -> ); //permitir escrita no textarea? abrir outra janela?
		
		//refresh
		Button buttonRefresh = new Button("Refresh");
		buttonRefresh.setOnAction(e -> setEmailList(listEmails, bodyEmail)); 
		
		optionsEmail.getChildren().addAll(buttonReply, buttonRefresh);
		return optionsEmail;
	}
	
}
