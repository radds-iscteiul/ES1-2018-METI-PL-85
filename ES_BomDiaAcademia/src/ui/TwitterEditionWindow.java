package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import engine.Controller;
import engine.Service;
import engine.TwitterService;
import utils.SpringUtilities;

public class TwitterEditionWindow extends ServiceEditionWindow{
	
	private JLabel keyLabel = new JLabel("Key: ",JLabel.TRAILING);
	private JTextField key;
	
	private JLabel secretLabel = new JLabel("Secret: ",JLabel.TRAILING);
	private JTextField secret;
	
	private JLabel tokenLabel = new JLabel("Token: ",JLabel.TRAILING);
	private JTextField token;
	
	private JLabel tokenSecretLabel = new JLabel("Token's secret: ",JLabel.TRAILING);
	private JTextField tokenSecret;

	public TwitterEditionWindow(TwitterService s) {
		super(s);
		
		fields.add(keyLabel,SpringLayout.EAST);
		key = new JTextField(s.getKey(),35);
		keyLabel.setLabelFor(key);
		fields.add(key);
		
		fields.add(secretLabel);
		secret = new JTextField(s.getSecret(),35);
		secretLabel.setLabelFor(secret);
		fields.add(secret);
		
		fields.add(tokenLabel,SpringLayout.EAST);
		token = new JTextField(s.getToken(),35);
		tokenLabel.setLabelFor(token);
		fields.add(token);
		
		fields.add(tokenSecretLabel);
		tokenSecret = new JTextField(s.getTokenSecret(),35);
		tokenSecretLabel.setLabelFor(tokenSecret);
		fields.add(tokenSecret);
		
		SpringUtilities.makeCompactGrid(fields, 6, 2 ,5, 5, 5, 5);
		this.add(fields,BorderLayout.CENTER);
		super.updateUI();
		
		this.setSaveListener();
	}
	
	@Override
	protected void setSaveListener() {
		super.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TwitterService twitterService = (TwitterService)service;
				twitterService.setUser(user.getText());
				twitterService.setPassword(password.getText());
				twitterService.setKey(key.getText());
				twitterService.setSecret(secret.getText());
				twitterService.setToken(token.getText());
				twitterService.setTokenSecret(tokenSecret.getText());
				
				Controller.getInstance().saveServicesToXML();
				
				
			}
		});
	}
}
