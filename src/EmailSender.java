import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Authenticator;


public class EmailSender {
	private Email emailToSend;
	private final String host = "localhost";
	private Properties properties;
	private Session session;
	
	public EmailSender(Email emailToSend){
		this.emailToSend = emailToSend;
		this.properties = new Properties();
		properties.setProperty("mail.smtp.host", host);
		this.session = Session.getDefaultInstance(properties, null);
	}
	
	public void sendEmail() throws MessagingException{
		try{
	        MimeMessage message = new MimeMessage(session);
	
	        message.setFrom(new InternetAddress(emailToSend.getSender()));
	
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailToSend.getRecipient()));
	
	        message.setSubject(emailToSend.getTitle());
	
	        message.setContent(emailToSend.getBody(), "text/html; charset=utf-8");
	
	        Transport.send(message);
	        System.out.println("Sent message successfully....");
		}
		catch(MessagingException e){
			e.printStackTrace();
		}
	}
	
	
	
	
}
