package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import config.XMLManager;

public class MainWindow extends JFrame{

	private JTextField path;
	private JButton search;
	private JButton launch;

	public MainWindow() {
		this.setLayout(new BorderLayout());
		this.startComponents();
		this.start();
	}
	
	private void startComponents() {
		path = new JTextField();
		path.setEditable(false);
		path.setPreferredSize(new Dimension(450, 25));
		
		search = new JButton("Search file");
		launch = new JButton("Launch");
		
		JPanel configurations = new JPanel();
		configurations.add(path);
		configurations.add(search);
		configurations.add(launch);
		this.add(configurations,BorderLayout.SOUTH);
		this.setListeners();
	}
	
	private void setListeners() {
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(MainWindow.this, "Choose a file", FileDialog.LOAD);
				fd.setFile("*.xml");
				fd.setVisible(true);
				String path = fd.getDirectory() + fd.getFile();
				if (path != null) {
					MainWindow.this.path.setText(path);
				}
				  
			}
		});
		launch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(path.getText().endsWith(".xml")){
					XMLManager xml = new XMLManager();
					try {
						xml.readXML(path.getText());
					} catch (ParserConfigurationException | SAXException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						MainWindow.this.path.setText("Erro ao carregar ficheiro,verifique a existência do mesmo");
					}
				}			
			}
		});
	}
	
	private void start() {
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		MainWindow mw = new MainWindow();
		
	}
}
