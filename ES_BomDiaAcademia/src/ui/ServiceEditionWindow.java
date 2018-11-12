package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import engine.Controller;
import engine.Service;
import engine.ServiceType;
import utils.SpringUtilities;

public class ServiceEditionWindow extends JFrame{
	
	protected Service service;

	JPanel fields;
	
	protected JLabel userLabel = new JLabel("User: ",JLabel.TRAILING);
	protected JTextField user;
	protected JLabel passwordLabel = new JLabel("Password: ",JLabel.TRAILING);
	protected JTextField password;
	
	protected JButton save = new JButton("Apply");
	protected JButton cancel = new JButton("Close");
	protected JButton fastSave = new JButton("Apply and Close");
	
	public ServiceEditionWindow(Service s) {
		this.setLayout(new BorderLayout());
		//this.setResizable(false);
		
		service = s;
		
		fields = new JPanel(new SpringLayout());
		
		fields.add(userLabel,SpringLayout.EAST);
		user = new JTextField(s.getUser(),15);
		userLabel.setLabelFor(user);
		fields.add(user);
		
		fields.add(passwordLabel);
		password = new JTextField(s.getPassword(),15);
		passwordLabel.setLabelFor(password);
		fields.add(password);
				
		SpringUtilities.makeCompactGrid(fields, 2, 2 ,5, 5, 5, 5);
		
		this.add(fields,BorderLayout.CENTER);
		
		JPanel buttons = new JPanel();
		buttons.add(save);
		buttons.add(cancel);
		buttons.add(fastSave);
		this.add(buttons,BorderLayout.SOUTH);
		
		this.setSaveListener();
		cancel.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiceEditionWindow.this.dispose();			
			}
		});
		fastSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(canSave(user.getText())) {
					save.doClick();
					cancel.doClick();
				}
			}
		});
		
		start();
	}

	protected boolean canSave(String word) {
		if(!word.contains("@gmail.com") && service.getName() == ServiceType.EMAIL) {
			System.out.println("Must be a gmail account");
			return false;
		} else {
			return true;
		}
	}
	protected void setSaveListener() {
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(canSave(user.getText())) {
					service.setUser(user.getText());
					service.setPassword(password.getText());
					System.out.println("New user: " + service.getUser() + "; New password: " + service.getPassword());
					Controller.getInstance().saveServicesToXML();
				}
				
			}
		});
	}
	private void start() {
		this.setLocationRelativeTo(null);
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	protected void updateUI() {
		this.pack();
		fields.updateUI();
	}
	
}
