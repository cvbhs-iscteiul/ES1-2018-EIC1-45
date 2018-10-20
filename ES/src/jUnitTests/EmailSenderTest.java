package jUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import mailApp.EmailSender;


/**
 * Date: 20/10/2018 
 * Classe de testes da classe EmailSender
 * @author António Teixeira
 * @version 1.0
 */

public class EmailSenderTest {

	String senderEmail = "es1_2018_45@outlook.pt";
	String senderPassword = "isctegrupo45";
	String receiverEmail = "es1_2018_45@outlook.pt";
	String emailSubject = "This is a test email subject";
	String emailBody = " This is a (very short) test email body";

	EmailSender send = new EmailSender(receiverEmail, emailSubject, emailBody, senderEmail, senderPassword);

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
