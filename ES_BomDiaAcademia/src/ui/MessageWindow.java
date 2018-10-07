package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import engine.Message;
import utils.SpringUtilities;

public class MessageWindow extends JFrame{
	
	private JLabel fromLabel = new JLabel("From: ",JLabel.TRAILING);
	private JTextField from;
	private JLabel dateLabel = new JLabel("Date: ",JLabel.TRAILING);
	private JTextField date;
	private JLabel headerLabel = new JLabel("Description: ",JLabel.TRAILING);
	private JTextField header;
	
	private JTextArea body;
	
	public MessageWindow(Message m) {

		this.setLayout(new BorderLayout());
		this.startComponents(m);
		this.start();

	}

	private void startComponents(Message m) {
		
		
		
		from = new JTextField(m.getFrom(),15);
		
		from.setEditable(false);
		
		
		date = new JTextField(m.getTime().toString(),15);
		
		date.setEditable(false);
		
		
		header = new JTextField(m.getHeader(),15);
		
		header.setEditable(false);
		
		body = new JTextArea(m.getMessage());	
		body.setEditable(false);
		
		JPanel labels = new JPanel(new SpringLayout());
		labels.add(fromLabel,SpringLayout.EAST);
		fromLabel.setLabelFor(from);
		labels.add(from);
		
		labels.add(dateLabel);
		dateLabel.setLabelFor(date);
		labels.add(date);
		
		labels.add(headerLabel);
		headerLabel.setLabelFor(header);
		labels.add(header);
		
		
		SpringUtilities.makeCompactGrid(labels,
                3, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
	
		
		this.add(labels,BorderLayout.NORTH);
		this.add(body,BorderLayout.CENTER);

	}
	
	private void start() {
		this.setPreferredSize(new Dimension(800, 500));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new MessageWindow(new Message("radds@iscte-iul.pt",new Date(),"Trabalho","Olá Rafael\n Sou o outro rafael, como está a correr o trabalho?"));
	}
}
