package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import engine.Controller;
import engine.MyMessage;
import engine.Service;
import engine.ServiceType;

public class InformationPanel extends JPanel{
	
	private MainWindow mainWindow;
	
	private JList<MyMessage> displayedMessages;
	private JButton composeMessage;
	
	
	public InformationPanel(MainWindow mw) {
		super();
		this.setLayout(new BorderLayout());
		
		this.mainWindow = mw;
		
		
		displayedMessages = new JList<MyMessage>();
		composeMessage = new JButton("Send email");
		
		JScrollPane scrollPane = new JScrollPane(displayedMessages);
		this.add(scrollPane,BorderLayout.CENTER);
		this.add(composeMessage,BorderLayout.SOUTH);
		
		Service email = Controller.getInstance().getService(ServiceType.EMAIL);
		this.setListeners(email.getUser(),email.getPassword());
		
		this.setVisible(true);
		
	}
	
	private void setListeners(String user,String pw) {
		composeMessage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SendEmailWindow(user, pw);				
			}
		});
		if(user == "" && pw == "") {
			composeMessage.setEnabled(false);
		}
	}

}
