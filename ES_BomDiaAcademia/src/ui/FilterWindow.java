package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
				leFicheiro(palavra.getText());

			}
		});


		norte.add(palavra);
		norte.add(enter);	


		frame.add(norte, BorderLayout.NORTH);
		frame.add(sul, BorderLayout.SOUTH);
	}

	public void leFicheiro(String palavra){
		File file = new File("./mensagem/testarFiltro.txt");

		try{
			Scanner	scanner = new Scanner(file);
			int l=0;
			String text="";
			while(scanner.hasNextLine()){
				String s = scanner.nextLine();
				text+=s + " ";

				for(int i = 0; ( i < text.length()-palavra.length()); i++) {

					String aux = text.substring(i, i+palavra.length());

					if( palavra.equals(aux)) {
						System.out.println("Found");

					}

				}

			}
			scanner.close();

		} catch(FileNotFoundException e){
			e.printStackTrace();
		}}


	public String getProcura() {
		return procura;
	}

	public static void main(String[] args) {
		FilterWindow f = new FilterWindow();

	}

}
