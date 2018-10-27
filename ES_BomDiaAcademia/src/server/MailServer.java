package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import engine.EmailMessage;

/**
 * 
 * @author Utilizador
 * 
 * This class is responsible for the methods Send and Receive Emails
 *
 */
public class MailServer {

	private String user; //metiG85_2018@gmail.com
	private String password; //RbDi1802&

	public MailServer(String u, String pw) {
		this.user = u;
		this.password = pw;
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param body
	 * 
	 * This method is responsible to get the session object (that stores all the information of host), compose and send the message
	 * Localhost has to be connected to the Internet
	 */
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
				return new PasswordAuthentication(user, password); //email e password 
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

	/**
	 * 
	 * @param pop3Host
	 * @param storeType
	 * @param user
	 * @param password
	 * 
	 * This method will contact the local email server and read and display the messages
	 * To check and fetch the emails, it's necessary: Folder and Store classes and POP server  
	 */
	public List<EmailMessage> receiveEmail(String user, String password) {

		List<EmailMessage> emailMessages = new ArrayList<EmailMessage>();
		Properties properties = new Properties();

		properties.put("mail.pop3.host", "pop.gmail.com");
		properties.put("mail.pop3.port", "995");
		properties.put("mail.pop3.starttls.enable", "true");

		Session emailSession= Session.getDefaultInstance(properties);
		try {

			Store emailStore = emailSession.getStore("pop3s");
			
			emailStore.connect("pop.gmail.com", user, password);
			System.out.println("Connect");

			Folder emailFolder = emailStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);
			System.out.println("inbox");

			Message[] messages = emailFolder.getMessages();
			System.out.println("messages.length---" + messages.length);

			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				emailMessages.add(new EmailMessage(message.getFrom()[0].toString(), user, message.getSubject(), new Date(), message.getContent().toString()));
				//System.out.println("---------------------------------");
				//System.out.println("Email Number " + (i + 1));
				//System.out.println("Subject: " + message.getSubject());
				//System.out.println("From: " + message.getFrom()[0]);
				//System.out.println("Text: " + message.getContent().toString());
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
		return emailMessages;
	}

}