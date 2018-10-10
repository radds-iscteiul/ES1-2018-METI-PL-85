package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import server.MailServer;
import utils.SpringUtilities;

public class EmailWindow extends JFrame{
	
	private String sender;
	private String password;
	
	public EmailWindow(String u, String pw){
		this.sender = u;
		this.password = pw;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(800, 500));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
		this.open();
	}
	
	public void open(){
		this.pack();
		this.setVisible(true);
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
				String fromAux = from.getText();
				String toAux = to.getText();
				String subjectAux = subject.getText();
				String bodyAux = body.getText();
				
				MailServer mailServer = new MailServer(EmailWindow.this.sender,EmailWindow.this.password);
				mailServer.sendEmail(fromAux, toAux, subjectAux, bodyAux);
			}
		});
		
		SpringUtilities.makeCompactGrid(labels, 3, 2, 5, 5, 5, 5);
		this.add(labels, BorderLayout.NORTH);
		this.add(body,BorderLayout.CENTER);
		this.add(send, BorderLayout.SOUTH);
		
	}
	

	public static void main(String[] args) {
		String user = "metiG85.2018@gmail.com";
		String password = "RbDi1802&";
		EmailWindow email = new EmailWindow(user,password);
	}
}