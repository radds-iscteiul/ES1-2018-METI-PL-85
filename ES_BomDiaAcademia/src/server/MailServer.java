package server;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.swing.JOptionPane;


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


}