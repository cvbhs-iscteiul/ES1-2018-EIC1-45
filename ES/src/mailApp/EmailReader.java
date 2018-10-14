package mailApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

public class EmailReader {

	private String receiverEmail;
	private String receiverPassword;
	private ArrayList<Mail> mails;

	public EmailReader(String receiverEmail, String receiverPassword) {
		this.receiverEmail = receiverEmail;
		this.receiverPassword = receiverPassword;
		mails = new ArrayList<Mail>();
	}

	public ArrayList<Mail> getMailList() {
		return mails;
	}

	private void getMail() {
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session mailSession = Session.getInstance(props);

			// mailSession.setDebug(true);
			Store emailStore = mailSession.getStore("imaps");
			emailStore.connect("imap-mail.outlook.com", receiverEmail, receiverPassword);

			// pasta inbox
			Folder emailFolder = emailStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);
			Message messages[] = emailFolder.getMessages();

//			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			// Só vejo as 3 primeiras

			for (int i = messages.length - 4; i < messages.length; i++) {
				Message message = messages[i];
				Address[] froms = message.getFrom(); //melhor maneira de extrair os endereços de quem enviou emails
				System.out.println("Email Number: " + (i + 1));
				String subject = message.getSubject();
				System.out.println("Subject: " + message.getSubject());
				String senderEmail = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
				System.out.println("From: " + senderEmail);
				Date sentDate = message.getSentDate();
				System.out.println("Sent date: " + sentDate);
				String messageBoby = getTextFromMessage(message);
				System.out.println("Text: " + messageBoby);

				Mail m = new Mail(receiverEmail, subject, messageBoby, senderEmail, sentDate);
				mails.add(m);
			}
			emailFolder.close();
			emailStore.close();
		} catch (NoSuchProviderException nspex) {
			nspex.printStackTrace();
		} catch (MessagingException mex) {
			mex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getTextFromMessage(Message message) throws IOException, MessagingException {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}

	private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws IOException, MessagingException {

		int count = mimeMultipart.getCount();
		if (count == 0)
			throw new MessagingException("Multipart with no body parts not supported.");
		boolean multipartAlt = new ContentType(mimeMultipart.getContentType()).match("multipart/alternative");
		if (multipartAlt)
			// alternatives appear in an order of increasing
			// faithfulness to the original content. Customize as req'd.
			return getTextFromBodyPart(mimeMultipart.getBodyPart(count - 1));
		String result = "";
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			result += getTextFromBodyPart(bodyPart);
		}
		return result;
	}

	private String getTextFromBodyPart(BodyPart bodyPart) throws IOException, MessagingException {

		String result = "";
		if (bodyPart.isMimeType("text/plain")) {
			result = (String) bodyPart.getContent();
		} else if (bodyPart.isMimeType("text/html")) {
			String html = (String) bodyPart.getContent();
			result = org.jsoup.Jsoup.parse(html).text();
		} else if (bodyPart.getContent() instanceof MimeMultipart) {
			result = getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
		}
		return result;
	}

	public static void main(String[] args) {

		String receiverEmail = "xxxxx@iscte-iul.pt";
		String receiverPassword = "xxxxxx";

		EmailReader read = new EmailReader(receiverEmail, receiverPassword);
		read.getMail();
	}

}
