package server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class mailServer {

	private JFrame frame;
	private static mailServer janela;
	
	public mailServer(){
		frame=new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(500,550);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
	}

	public void open(){
		frame.pack();
		frame.setVisible(true);
	}

	private void addFrameContent() {

		JPanel panel = new JPanel();

		JButton mail = new JButton("Mail");
		mail.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465"); //port para SSL configuraçao standart
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");
				
				Session session=Session.getDefaultInstance(props,
						new javax.mail.Authenticator(){
						protected PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication("xxx@gmail.com", "pass"); //email e password 
						}
						}
						
						);
				System.out.println("sessao feita");
				try{
					System.out.println("entrou no try");
				Message message = new MimeMessage(session);	
				message.setFrom(new InternetAddress("xxx@gmail.com"));	
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("97bcosta@gmail.com")); 
				message.setSubject("Olá");
				message.setText("estou a enviar um email pelo eclipse");
				System.out.println("Antes do transporte");
				Transport.send(message);
				JOptionPane.showMessageDialog(null, "message sent");
				System.out.println("Mensagem enviada!");
				
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
					System.out.println("dentro do catch");
				}
			}
		});
		panel.add(mail);	

		frame.add(panel, BorderLayout.NORTH);
	}

	public static void main(String[] args) {
		janela = new mailServer();
		janela.open();
	}

}