package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import engine.Controller;
import engine.FacebookService;
import utils.SpringUtilities;

public class FacebookEditionWindow extends ServiceEditionWindow{
	
	private JLabel tokenLabel = new JLabel("Token: ",JLabel.TRAILING);
	private JTextField token;

	public FacebookEditionWindow(FacebookService s) {
		super(s);

		fields.add(tokenLabel,SpringLayout.EAST);
		token = new JTextField(s.getToken(),35);
		tokenLabel.setLabelFor(token);
		fields.add(token);
		
		SpringUtilities.makeCompactGrid(fields, 3, 2 ,5, 5, 5, 5);
		this.add(fields,BorderLayout.CENTER);
		super.updateUI();
		
		this.setSaveListener();
	}
	
	@Override
	protected void setSaveListener() {
		super.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FacebookService facebookService = (FacebookService)service;
				facebookService.setToken(token.getText());
				Controller.getInstance().saveServicesToXML();			
			}
		});
	}

}
