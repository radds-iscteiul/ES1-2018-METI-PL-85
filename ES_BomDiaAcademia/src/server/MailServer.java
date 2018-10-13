package server;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.swing.JOptionPane;
import java.io.IOException;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import com.sun.mail.pop3.POP3Store;

import com.sun.mail.pop3.POP3Store;


public class MailServer {

	private String sender; //metiG85_2018@gmail.com
	private String password; //RbDi1802&
	
	public MailServer(String u, String pw) {
		this.sender = u;
		this.password = pw;
	}

	public void sendEmail(String from, String to, String subject, String body) {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465"); //port para SSL configuraçao standart
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
				
		Session session=Session.getDefaultInstance(props,
				new javax.mail.Authenticator(){
					protected PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication(sender, password); //email e password 
					}
				}		
				);
				try{	
					Message message = new MimeMessage(session);	
					message.setFrom(new InternetAddress(from));	
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); 
					message.setSubject(subject);
					message.setText(body);
				
					Transport.send(message);
					JOptionPane.showMessageDialog(null, "message sent");
								
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
					System.out.println("dentro do catch");
				}
	}

	public void receiveEmail(String pop3Host, String storeType, String user, String password) {
//		pop3host = pop.gmail.com
//		storeType = pop3
		
		try {
		   Properties properties = new Properties();
		   properties.put("mail.pop3.host", pop3Host);
		   Session emailSession = Session.getDefaultInstance(properties);

		   POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
		   emailStore.connect(user, password);

		   Folder emailFolder = emailStore.getFolder("INBOX");
		   emailFolder.open(Folder.READ_ONLY);

		   Message[] messages = emailFolder.getMessages();
		   for (int i = 0; i < messages.length; i++) {
			Message message = messages[i];
			System.out.println("---------------------------------");
			System.out.println("Email Number " + (i + 1));
			System.out.println("Subject: " + message.getSubject());
			System.out.println("From: " + message.getFrom()[0]);
			System.out.println("Text: " + message.getContent().toString());
		   }

		   emailFolder.close(false);
		   emailStore.close();

		  } catch (NoSuchProviderException e) {
			  e.printStackTrace();
		  } 
		  catch (MessagingException e) {
			  e.printStackTrace();
		  }
		  catch (IOException e) {
			  e.printStackTrace();
		  }
		
		 }
	
}