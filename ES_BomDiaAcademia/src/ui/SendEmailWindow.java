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

public class SendEmailWindow extends MessageWindow{
	
	private String sender;
	private String password;
	private JButton send;
	
	public SendEmailWindow(String u, String pw){
		super();
		this.addFrameContent();
	}
	
	
	
	public void open(){
		this.pack();
		this.setVisible(true);
	}
	
	private void addFrameContent(){

		send = new JButton("Send");
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String fromAux = from.getText();
				String toAux = to.getText();
				String subjectAux = subject.getText();
				String bodyAux = body.getText();
				
				MailServer mailServer = new MailServer(SendEmailWindow.this.sender,SendEmailWindow.this.password);
				mailServer.sendEmail(fromAux, toAux, subjectAux, bodyAux);
			}
		});
		this.add(send, BorderLayout.SOUTH);
		
	}
	

	public static void main(String[] args) {
		String user = "metiG85.2018@gmail.com";
		String password = "RbDi1802&";
		SendEmailWindow email = new SendEmailWindow(user,password);
	}
}