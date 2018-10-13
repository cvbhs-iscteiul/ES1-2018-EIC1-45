package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowMailReply {

	public static void openReply() { // deve receber o numero do email para poder responder
		// Stage
		Stage windowMailReply = new Stage();
		windowMailReply.setTitle("Reply window");
		windowMailReply.initModality(Modality.APPLICATION_MODAL);

		Scene sceneMailReply = getSceneReplyMail();

		// Stage Show
		windowMailReply.setScene(sceneMailReply); // default
		windowMailReply.showAndWait();
	}

	private static Scene getSceneReplyMail() {
		VBox frame = new VBox(10);
		frame.setPadding(new Insets(10));
		frame.setAlignment(Pos.TOP_LEFT);
		
		TextArea bodyReply = new TextArea();
		bodyReply.prefWidthProperty().bind(frame.widthProperty());
		bodyReply.prefHeightProperty().bind(frame.heightProperty());
		
		Button buttonSend = new Button("Send");
		buttonSend.setOnAction(e -> sendReply(bodyReply.getText()));
		
		
		frame.getChildren().addAll(buttonSend, bodyReply);
		
		return new Scene(frame, 600, 500);
	}

	private static void sendReply(String replyText) { //ir buscar o texto e remetente
		// TODO Auto-generated method stub
	}

}
