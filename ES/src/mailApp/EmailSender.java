package mailApp;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Date: 25/10/2018 Classe para enviar emails
 * 
 * @author António Teixeira
 * @version 1.02
 */

public class EmailSender {

	/**
	 * Valor String para o endereço de email de quem recebe
	 */
	private String receiverEmail;
	/**
	 * Valor String para o assunto do email
	 */
	private String emailSubject;
	/**
	 * Valor String para o corpo do email
	 */
	private String emailBody;
	/**
	 * Valor String para o endereço de email de quem envia
	 */
	private String senderEmail;
	/**
	 * Valor String para a password de email de quem envia
	 */
	private String senderPassword;

	/**
	 * Construtor da classe EmailSender
	 * 
	 * @param receiverEmail  primeiro argumento String
	 * @param emailSubject   segundo argumento String
	 * @param emailBody      terceiro argumento String
	 * @param senderEmail    quarto argumento String
	 * @param senderPassword quinto argumento String
	 */
	public EmailSender(String receiverEmail, String emailSubject, String emailBody, String senderEmail,
			String senderPassword) {
		this.receiverEmail = receiverEmail;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
		this.senderEmail = senderEmail;
		this.senderPassword = senderPassword;
	}

	/**
	 * Método que envia uma mensagem de email
	 */
	public void sendEmail() {
		Properties props = new Properties();
		props.put("mail.smtp.user", senderEmail);
		props.put("mail.smtp.host", "smtp-mail.outlook.com");
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.pwd", senderPassword);
		props.put("mail.smtp.port", 587);

		try {
			Authenticator auth = new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(senderEmail, senderPassword);
				}
			};

			Session session = Session.getInstance(props, auth);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(emailBody);
			msg.setSubject(emailSubject);
			msg.setFrom(new InternetAddress(senderEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
			Transport.send(msg);

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		System.out.println("Done. Mail sent!");
	}

	/**
	 * Método Main desta classe que serve para testar as funcionalidades da mesma
	 * 
	 * @param args String de argumentos
	 */
	public static void main(String[] args) {

		String senderEmail = "es1_2018_45@outlook.pt";
		String senderPassword = "isctegrupo45";
		String receiverEmail = "es1_2018_45@outlook.pt";
		String emailSubject = "This is a test email subject X";
		String emailBody = "This is a test email body X";

		EmailSender send = new EmailSender(receiverEmail, emailSubject, emailBody, senderEmail, senderPassword);
		send.sendEmail();
	}

}
