package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import config.XMLManager;
import engine.Controller;
import engine.Service;
/**
 * 
 * @author Rafael Dias
 *
 */
public class ConfigurationWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField path;
	private JButton search;
	private JButton launch;

	public ConfigurationWindow() {
		this.setLayout(new BorderLayout());
		this.setResizable(false);
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
				FileDialog fd = new FileDialog(ConfigurationWindow.this, "Choose a file", FileDialog.LOAD);
				fd.setFile("*.xml");
				fd.setVisible(true);
				String path = fd.getDirectory() + fd.getFile();
				if (path != null) {
					ConfigurationWindow.this.path.setText(path);
				}
				  
			}
		});
		launch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(path.getText().endsWith(".xml")){
					XMLManager xml = new XMLManager();
					try {
						ArrayList<Service> lista = xml.readXML(path.getText());		
						Controller.getInstance().startMyController(lista);
				
						ConfigurationWindow.this.dispose();
					} catch (ParserConfigurationException | SAXException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						ConfigurationWindow.this.path.setText("Erro ao carregar ficheiro,verifique a existência do mesmo");
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
		ConfigurationWindow mw = new ConfigurationWindow();
		
	}
}
