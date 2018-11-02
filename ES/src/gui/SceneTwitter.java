package gui;


import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SceneTwitter{

	public static Scene getScene() {
		BorderPane frame = new BorderPane();
		frame.setTop(WindowMain.buttonsTop());

		
		//body of email and options
		VBox optionsAndContent = new VBox(10);
		//tweet and reply
		TextArea tweet = new TextArea();
		tweet.setEditable(false);
		tweet.setWrapText(true);
		tweet.setPadding(new Insets(10, 0, 0, 10));
		tweet.prefWidthProperty().bind(optionsAndContent.widthProperty());
		tweet.prefHeightProperty().bind(Bindings.divide(optionsAndContent.heightProperty(), 2));
		tweet.setWrapText(true);
		tweet.setPadding(new Insets(0, 10, 10, 0));
		TextArea tweetResponse = new TextArea();
		tweetResponse.prefWidthProperty().bind(optionsAndContent.widthProperty());
		tweetResponse.prefHeightProperty().bind(Bindings.divide(optionsAndContent.heightProperty(), 2));
		tweetResponse.setWrapText(true);
		
		//left list of tweets 
		ListView<String> listTweets = new ListView<>();
		setTweetList(listTweets, tweet);
		frame.setLeft(listTweets);
		
		optionsAndContent.getChildren().addAll(buttonsOptionsEmail(listTweets, tweet), tweet, tweetResponse);
		frame.setCenter(optionsAndContent);
		
		return new Scene(frame, WindowMain.WIDTH, WindowMain.HEIGHT);
		
	}
	
	private static void setTweetList(ListView<String> listEmails, TextArea bodyEmail) {
		if (listEmails.getItems() != null)
			listEmails.getItems().clear();
		listEmails.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
			bodyEmail.setText(funcGetTextMail(listEmails.getSelectionModel().getSelectedIndex())); //função para ir buscar o texto do email aqui
		});
		//Função da class Mail que devolve um ArrayList com os títulos dos emails
		ArrayList<String> arrayListEmails = new ArrayList<>();//Mail.getEmailList(); 
		//temp enquanto nao ha funçao do mail
		for (int i = 0; i < 26; i++) {
			arrayListEmails.add("Título do tweet LOOOOONGOOOOOOOOOOOO " + i + " 25/02/2018\nRemetente");
		}
		for (String email : arrayListEmails) {
			listEmails.getItems().add(email);
		}listEmails.getSelectionModel().select(0);
	}
	

	private static String funcGetTextMail(int selectedIndex) { // funcao para imitar a que se vai usar da classe do email
		String body = "";
		for (int i = 0; i < 36; i++) {
			body += "corpo do tweet" + selectedIndex + " ";
		}
		return body;
	}

	private static HBox buttonsOptionsEmail(ListView<String> listTweets, TextArea tweet) {
		HBox optionsEmail = new HBox(10);
		optionsEmail.setPadding(new Insets(0, 0, 0, 10));
		optionsEmail.setAlignment(Pos.BASELINE_LEFT);
		
		Button buttonReply = new Button("Reply");
//		buttonReply.setOnAction(e -> ); //responde diretamente
		
		//refresh
		Button buttonRefresh = new Button("Refresh");
		buttonRefresh.setOnAction(e -> setTweetList(listTweets, tweet)); 
		
		//filters
		TextField textFilter = new TextField();
		textFilter.setPromptText("Filters");
		Button buttonFilter = new Button("Apply");
//		buttonFilter.setOnAction(e -> );
		
		optionsEmail.getChildren().addAll(buttonReply, buttonRefresh, textFilter, buttonFilter);
		return optionsEmail;
	}
	
}
