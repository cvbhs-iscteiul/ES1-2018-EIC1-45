package mailApp;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class EmailReader {

	private String receiverEmail;
	private String receiverPassword;

	public EmailReader(String receiverEmail, String receiverPassword) {
		this.receiverEmail = receiverEmail;
		this.receiverPassword = receiverPassword;
	}

	private void getMail() {
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session mailSession = Session.getInstance(props);

			// mailSession.setDebug(true);
			Store emailStore = mailSession.getStore("imaps"); // imap ?
			emailStore.connect("imap-mail.outlook.com", receiverEmail, receiverPassword);

			// pasta inbox
			Folder emailFolder = emailStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);
			Message messages[] = emailFolder.getMessages();

			// Só vejo as 3 primeiras
			for (int i = messages.length - 3; i < messages.length; i++) {
				Message message = messages[i];
				System.out.println("Email Number: " + (i + 1));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Sent date: " + message.getSentDate());
			}
			System.out.println(emailStore);
			emailFolder.close();
			emailStore.close();
		} catch (NoSuchProviderException nspex) {
			nspex.printStackTrace();
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String receiverEmail = "xxxxx@iscte-iul.pt";
		String receiverPassword = "xxxxxxx";

		EmailReader read = new EmailReader(receiverEmail, receiverPassword);
		read.getMail();
	}

}
