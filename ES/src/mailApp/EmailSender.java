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

public class EmailSender {
	private String receiverEmail;
	private String emailSubject;
	private String emailBody;
	private String senderEmail;
	private String senderPassword;

	public EmailSender(String receiverEmail, String emailSubject, String emailBody, String senderEmail,
			String senderPassword) {
		this.receiverEmail = receiverEmail;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
		this.senderEmail = senderEmail;
		this.senderPassword = senderPassword;
	}

	private void sendEmail() {
		Properties props = new Properties();
		props.put("mail.smtp.user", senderEmail);
		props.put("mail.smtp.host", "smtp-mail.outlook.com");
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.pwd", senderPassword);

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

	public static void main(String[] args) {

		String senderEmail = "xxx@iscte-iul.pt";
		String senderPassword = "xxxxx";

		String receiverEmail = "xxx@iscte-iul.pt";
		String emailSubject = "This is a test email subject";
		String emailBody = " This is a test email body";

		EmailSender send = new EmailSender(receiverEmail, emailSubject, emailBody, senderEmail, senderPassword);
		send.sendEmail();
	}

}
