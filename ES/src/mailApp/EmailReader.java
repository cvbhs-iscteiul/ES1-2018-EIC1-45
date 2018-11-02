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

/**
 * Date: 25/10/2018 Classe para ler emails na consola
 * 
 * @author António Teixeira
 * @version 1.02
 *
 */

public class EmailReader {

	/**
	 * Valor String para o endereço de email de quem recebe
	 */
	private String receiverEmail;
	/**
	 * Valor String para a password do email de quem recebe
	 */
	private String receiverPassword;
	/**
	 * Lista de emails recebidos (a aplicar no futuro)
	 */
	private ArrayList<Mail> mails;

	/**
	 * Construtor da classe EmailReader
	 * 
	 * @param receiverEmail    primeiro argumento String
	 * @param receiverPassword segundo argumento String
	 */
	public EmailReader(String receiverEmail, String receiverPassword) {
		this.receiverEmail = receiverEmail;
		this.receiverPassword = receiverPassword;
		mails = new ArrayList<Mail>();
		
		// HHH
	}

	public ArrayList<Mail> getMailList() {
		return mails;
	}

	/**
	 * Método que mostra na consola as 3 ultimas mensagens da caixa do correio
	 */
	public void getMail() {
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

			// Aqui posso configurar o numero de mensagens mostradas.

			for (int i = messages.length - 3; i < messages.length; i++) {
				Message message = messages[i];
				Address[] froms = message.getFrom(); // melhor maneira de extrair os endereços de quem enviou emails
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

	/**
	 * Método auxiliar do getMail() que devolve o texto da mensagem de mail caso
	 * seja do tipo Mime.
	 * 
	 * @param message argumento do tipo Message.
	 * @return Devolve uma String que corresponde ao texto da mensagem de email.
	 * @throws IOException        envia a exceção do tipo IOException para quem
	 *                            chama o método.
	 * @throws MessagingException envia a exceção do tipo MessagingException para
	 *                            quem chama o método.
	 */
	public String getTextFromMessage(Message message) throws IOException, MessagingException {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}

	/**
	 * Método auxiliar do getTextFromMessage() que devolve o texto da mensagem de
	 * mail caso seja do tipo mimeMultipart.
	 * 
	 * @param mimeMultipart argumento do tipo mimeMultipart
	 * @return Devolve uma String que corresponde ao texto da mensagem de email
	 * @throws IOException        envia a exceção do tipo IOException para quem
	 *                            chama o método.
	 * @throws MessagingException envia a exceção do tipo MessagingException para
	 *                            quem chama o método.
	 */
	public String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws IOException, MessagingException {

		int count = mimeMultipart.getCount();
		if (count == 0)
			throw new MessagingException("Multipart with no body parts not supported.");
		boolean multipartAlt = new ContentType(mimeMultipart.getContentType()).match("multipart/alternative");
		if (multipartAlt)
			// alternativas aparecem pela ordem crescente
			return getTextFromBodyPart(mimeMultipart.getBodyPart(count - 1));
		String result = "";
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			result += getTextFromBodyPart(bodyPart);
		}
		return result;
	}

	/**
	 * Método auxiliar de getTextFromMimeMultipart() que devolve o texto da mensagem
	 * de email caso seja do tipo multipartAlt
	 * 
	 * @param bodyPart argumento do tipo bodyPart
	 * @return Devolve uma String que corresponde ao texto da mensagem de email
	 * @throws IOException        envia a exceção do tipo IOException para quem
	 *                            chama o método.
	 * @throws MessagingException envia a exceção do tipo MessagingException para
	 *                            quem chama o método.
	 */
	public String getTextFromBodyPart(BodyPart bodyPart) throws IOException, MessagingException {
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

	/**
	 * Método Main desta classe que serve para testar as funcionalidades da mesma
	 * 
	 * @param args String de argumentos
	 */
	public static void main(String[] args) {

		String receiverEmail = "es1_2018_45@outlook.pt";
		String receiverPassword = "isctegrupo45";

		EmailReader read = new EmailReader(receiverEmail, receiverPassword);
		read.getMail();
	}

}
