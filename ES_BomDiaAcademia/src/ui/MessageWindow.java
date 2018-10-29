package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import utils.SpringUtilities;
/**
 * 
 * @author Rafael Dias
 *
 */
public class MessageWindow extends JFrame{
	
	protected JLabel fromLabel = new JLabel("From: ",JLabel.TRAILING);
	protected JTextField from;
	protected JLabel toLabel = new JLabel("To: ",JLabel.TRAILING);
	protected JTextField to;
	protected JLabel dateLabel = new JLabel("Date: ",JLabel.TRAILING);
	protected JTextField date;
	protected JLabel subjectLabel = new JLabel("Subject: ",JLabel.TRAILING);
	protected JTextField subject;
	
	protected JTextArea body;
	
	public MessageWindow() {
		super();
		this.setLayout(new BorderLayout());
		this.startComponents();
		this.start();
	}

	private void startComponents() {
		
		JPanel labels = new JPanel(new SpringLayout());
		
		labels.add(fromLabel,SpringLayout.EAST);
		from = new JTextField(" ", 15);
		fromLabel.setLabelFor(from);
		labels.add(from);
		
		labels.add(toLabel);
		to = new JTextField(" ", 15);
		toLabel.setLabelFor(to);
		labels.add(to);
		
		labels.add(subjectLabel);
		subject = new JTextField(" ", 15);
		subjectLabel.setLabelFor(subject);
		labels.add(subject);
		
		body= new JTextArea ();
		
		SpringUtilities.makeCompactGrid(labels, 3, 2, 5, 5, 5, 5);
		this.add(labels, BorderLayout.NORTH);
		this.add(body,BorderLayout.CENTER);
		
	}
	
	private void start() {
		this.setPreferredSize(new Dimension(800, 500));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new MessageWindow();
	}
}
