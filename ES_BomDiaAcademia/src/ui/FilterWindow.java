package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import engine.MyMessage;



public class FilterWindow {

	private JFrame frame;
	private String procura="";
	private MyMessage message;


	public FilterWindow(){
		frame=new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(500,550);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
		open();
	}

	
	public void open(){
		frame.pack();
		frame.setVisible(true);
	}

	private void addFrameContent() {

		JPanel norte = new JPanel();
		JPanel sul = new JPanel();

		JTextField palavra = new JTextField();
		palavra.setPreferredSize(new Dimension (200,30));

		JEditorPane text = new JEditorPane("text/html", "");

		JButton enter = new JButton("Search");
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				procura = palavra.getText();
				procurar(message, procura);
				

			}
		});


		norte.add(palavra);
		norte.add(enter);	


		frame.add(norte, BorderLayout.NORTH);
		frame.add(sul, BorderLayout.SOUTH);
	}

	


	public String getProcura() {
		return procura;
	}

	public MyMessage procurar (MyMessage message, String palavra){
		String m = message.getMessage();
		
          
		for(int i = 0; ( i < m.length()-palavra.length()); i++) {

			String aux = m.substring(i, i+palavra.length());
			if( palavra.equals(aux)) {
				System.out.println("Found");
			}
			
		}
		if (m != null && m.toLowerCase().contains(palavra)) {
				System.out.println("Palavra encontrada na mensagem : " + palavra );		
		}
		
		return message;
	}



	public static void main(String[] args) {
		String f = "imlde";
		String t = "boss";
		Date d =  new Date(0);
		String h = "teste";
		String m = "vamos procurar uma palavra nesta mensagem";
		String palavra = "procurar uma palavra";
		MyMessage msg = new MyMessage(f, t, d, h, m);
		FilterWindow fw = new FilterWindow();
		fw.procurar(msg, palavra);
		
	}

}
