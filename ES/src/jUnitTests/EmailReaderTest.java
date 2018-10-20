package jUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import mailApp.EmailReader;
import mailApp.Mail;

/**
 * Date: 20/10/2018
 * Classe de testes da classe EmailReader
 * @author António Teixeira
 * @version 1.0
 */

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
