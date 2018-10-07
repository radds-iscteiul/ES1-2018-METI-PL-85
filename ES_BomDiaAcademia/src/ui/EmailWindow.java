package ui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import utils.SpringUtilities;

public class EmailWindow {
	
	private JFrame frame;
	
	public EmailWindow(){
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setPreferredSize(new Dimension(800, 500));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
	}
	
	public void open(){
		frame.pack();
		frame.setVisible(true);
	}
	
	private void addFrameContent(){
		JPanel labels = new JPanel(new SpringLayout());
		
		JLabel fromLabel = new JLabel("From: ", JLabel.TRAILING);
		labels.add(fromLabel,SpringLayout.EAST);
		JTextField from = new JTextField(" ", 15);
		fromLabel.setLabelFor(from);
		labels.add(from);
		
		JLabel toLabel = new JLabel("To: ", JLabel.TRAILING);
		labels.add(toLabel);
		JTextField to = new JTextField(" ", 15);
		toLabel.setLabelFor(to);
		labels.add(to);
		
		JLabel subjectLabel = new JLabel("Subject: ", JLabel.TRAILING);
		labels.add(subjectLabel);
		JTextField subject = new JTextField(" ", 15);
		subjectLabel.setLabelFor(subject);
		labels.add(subject);
		
		JTextArea body= new JTextArea ();
		
		JButton send = new JButton("Send");
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String From = from.getText();
				String To = to.getText();
				String Subject = subject.getText();
				String Body = body.getText();
				
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
				message.setFrom(new InternetAddress(From));	
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(To)); 
				message.setSubject(Subject);
				message.setText(Body);
				System.out.println("mensagem pronta a enviar");
				Transport.send(message);
				
				JOptionPane.showMessageDialog(null, "message sent");
				System.out.println("Mensagem enviada!");
				
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
					System.out.println("dentro do catch");
				}
			}
		});
		
		SpringUtilities.makeCompactGrid(labels, 3, 2, 5, 5, 5, 5);
		frame.add(labels, BorderLayout.NORTH);
		frame.add(body,BorderLayout.CENTER);
		frame.add(send, BorderLayout.SOUTH);
		
	}
	

	public static void main(String[] args) {
		EmailWindow email =new EmailWindow();
		email.open();
	}
}