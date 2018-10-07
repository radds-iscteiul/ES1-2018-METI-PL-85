package ui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import utils.SpringUtilities;

public class EmailWindow {
	
	private JFrame frame;
	
	public EmailWindow(){
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setPreferredSize(new Dimension(800, 500));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
	}
	
	public void open(){
		frame.pack();
		frame.setVisible(true);
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
		
		SpringUtilities.makeCompactGrid(labels, 3, 2, 5, 5, 5, 5);
		frame.add(labels, BorderLayout.NORTH);
		frame.add(body,BorderLayout.CENTER);
		frame.add(send, BorderLayout.SOUTH);
		
	}
	
	public static void main(String[] args) {
		EmailWindow email =new EmailWindow();
		email.open();
	}
}