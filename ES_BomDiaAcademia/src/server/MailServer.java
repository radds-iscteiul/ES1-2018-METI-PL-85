package server;

import java.util.Properties;
import javax.mail.Message;
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


public class MailServer {

	private String sender;
	private String password;
	
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

		try {
//			objeto de sessão
			Properties properties = new Properties();
			properties.put("mail.pop3.host", pop3Host);
			Session emailSession = Session.getDefaultInstance(properties);
		
//			criação da POP3 Message Store e conecção com o "pop server"
			POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
			emailStore.connect(user, password);

//			criação e abertura do objeto ficheiro
			Folder emailFolder = emailStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

//			recuperar mensagens do ficheiro e imprimi-las
			Message[] messages = emailFolder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				System.out.println("==============================");
				System.out.println("Email Number " + (i + 1));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Text: " + message.getContent().toString());
			}

			emailFolder.close(false);
			emailStore.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}