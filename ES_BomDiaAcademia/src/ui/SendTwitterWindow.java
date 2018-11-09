package ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import utils.SpringUtilities;

public class SendTwitterWindow extends SendWindow{

	private JButton loadImage;
	private JButton loadGIF;
	
	public SendTwitterWindow() {
		super();
		this.startComponents();
		this.pack();
	}
	
	private void startComponents() {
		service = new JLabel("Tweet",JLabel.TRAILING);
		service.setFont(new Font("Serif", Font.BOLD, 22));
		labels.add(service);
		SpringUtilities.makeCompactGrid(labels, 1, 1, 5, 5, 5, 5);
		this.add(labels, BorderLayout.NORTH);
		
		body.setText("What's happening?");
		
		loadImage = new JButton("Load Image");
		loadGIF = new JButton("Load GIF");
		this.buttons.add(loadImage);
		this.buttons.add(loadGIF);
		this.add(buttons, BorderLayout.SOUTH);
	}
	
	
	public static void main(String[] args) {
		new SendTwitterWindow();
	}
}
