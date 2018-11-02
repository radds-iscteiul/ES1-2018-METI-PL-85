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
import utils.SpringUtilities;

public class ServiceEditionWindow extends JFrame{
	
	private Service service;
	
	private JLabel userLabel = new JLabel("User: ",JLabel.TRAILING);
	private JTextField user;
	private JLabel passwordLabel = new JLabel("Password: ",JLabel.TRAILING);
	private JTextField password;
	
	private JButton save = new JButton("Apply");
	private JButton cancel = new JButton("Close");
	private JButton fastSave = new JButton("Apply and Close");
	
	public ServiceEditionWindow(Service s) {
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		
		service = s;
		
		JPanel fields = new JPanel(new SpringLayout());
		
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
		
		this.setListeners();
		start();
	}

	private boolean canSave(String word) {
		if(word.contains("@gmail.com")) {
			return true;
		} else {
			System.out.println("Must be a gmail account");
			return false;
		}
	}
	private void setListeners() {
		cancel.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiceEditionWindow.this.dispose();			
			}
		});
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
		fastSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(canSave(user.getText())) {
					save.doClick();
					cancel.doClick();
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
	
	public static void main(String[] args) {
		ArrayList<Service> services = new ArrayList<>();
		services.add(new Service(1, "Email", "Rafael@gmail.com", "12345",true));
		services.add(new Service(2, "Facebook", "Rafael", "fb12345",false));
		Controller.getInstance().startMyController(services);
		new ServiceEditionWindow(services.get(0));
	}
}
