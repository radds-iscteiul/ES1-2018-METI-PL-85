package ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import utils.SpringUtilities;

public class SendTwitterWindow extends SendWindow{

	protected JLabel service;// = new JLabel("Tweet",JLabel.TRAILING);
	protected JLabel message;// = new JLabel("What's happening?",JLabel.TRAILING);
	protected JPanel labels;// = new JPanel(new SpringLayout());
	
	private JButton loadImage;
	private JButton loadGIF;
	
	public SendTwitterWindow() {
		super();
		this.startComponents();
		this.pack();
	}
	
	private void startComponents() {
		
		labels = new JPanel(new SpringLayout());
		service = new JLabel("Tweet",JLabel.TRAILING);
		message = new JLabel("What's happening?",JLabel.TRAILING);
		labels.add(service);
		labels.add(message);
		loadImage = new JButton("Load Image");
		loadGIF = new JButton("Load GIF");
		this.add(loadImage, BorderLayout.SOUTH);
		
		SpringUtilities.makeCompactGrid(labels, 2, 1, 5, 5, 5, 5);
		this.add(labels, BorderLayout.NORTH);
	}
	
	
	public static void main(String[] args) {
		new SendTwitterWindow();
	}
}
