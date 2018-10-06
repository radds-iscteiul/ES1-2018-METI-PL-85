package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import engine.Service;

public class MainWindow extends JFrame{

	Map<String,Service> servicos;
	
	public MainWindow(ArrayList<Service> s) {
		servicos = new HashMap<String,Service>();
		for (Service service : s) {
			servicos.put(service.getEndereco(), service);
		}
		this.setName("BDA - Bom dia academia");
		this.setLayout(new BorderLayout());
		this.add(new ServicePanel(servicos),BorderLayout.EAST);
		this.start();
	}
	
	private void start() {
		this.setSize(new Dimension(800,500));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
