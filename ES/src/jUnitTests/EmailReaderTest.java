package jUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import mailApp.EmailReader;
import mailApp.Mail;

public class EmailReaderTest {

	String receiverEmail = "es1_2018_45@outlook.pt";
	String receiverPassword = "isctegrupo45";

	EmailReader er = new EmailReader(receiverEmail, receiverPassword);
	Mail m = new Mail(null, null, null, null, null);

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
