package mailApp;

import java.util.Date;

public class Mail {

	private String receiverEmail;
	private String emailSubject;
	private String emailBody;
	private String senderEmail;
	private Date sentDate;

	public Mail(String receiverEmail, String emailSubject, String emailBody, String senderEmail, Date sentDate) {
		this.sentDate = sentDate;
		this.receiverEmail = receiverEmail;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
		this.senderEmail = senderEmail;
	}

}
