package Util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	
	public static void sendEmail(String to, String from, String subject, String body, boolean bodyisHTML) throws MessagingException {
		//get a mail session
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", "465");
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getInstance(props);
		session.setDebug(true);
		
		//create a message 
		Message message = new MimeMessage(session);
		message.setSubject(subject);
		if(bodyisHTML) 
			message.setContent(body, "text/html");
		else
			message.setText(body);
		
		//address the message
		Address fromAddress = new InternetAddress(from);
		Address toAddress = new InternetAddress(to);
		message.setFrom(fromAddress);
		message.setRecipient(Message.RecipientType.TO, toAddress);
		
		//send the message
		Transport transport = session.getTransport("smtp");
		transport.connect("elmansouri.houssam@gmail.com", "Abc@102030.");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		
		
    }
	
	
}
