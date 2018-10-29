package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import utils.SpringUtilities;

public class SendWindow extends JFrame{

	private JButton send;
	protected JTextArea body;
	//protected JPanel labels = new JPanel(new SpringLayout());
	
	public SendWindow() {
		super();
		this.setLayout(new BorderLayout());
		this.startComponents();
		this.start();
	}
	private void startComponents() {
		
		//JPanel labels = new JPanel(new SpringLayout());
		//JLabel service = new JLabel("Tweet",JLabel.TRAILING);
	//	JLabel message = new JLabel("What's happening?",JLabel.TRAILING);
//		labels.add(service);
//		labels.add(message);
		
		body= new JTextArea ();
		send = new JButton("Send");
		//SpringUtilities.makeCompactGrid(labels, 2, 1, 5, 5, 5, 5);
		//this.add(labels, BorderLayout.NORTH);
		this.add(body,BorderLayout.CENTER);
		this.add(send,BorderLayout.SOUTH);
		
	}
	
	private void start() {
		this.setPreferredSize(new Dimension(800, 500));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new SendWindow();
	}
}
