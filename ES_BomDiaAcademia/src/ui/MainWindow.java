package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import engine.Controller;

public class MainWindow extends JFrame{

	public MainWindow() {
		super("BDA - Bom dia academia");
		this.setLayout(new BorderLayout());
				
		this.add(new ServicePanel(this),BorderLayout.EAST);
		this.add(new InformationPanel(this),BorderLayout.CENTER);
		this.start();
	}
	
	private void start() {
		this.setSize(new Dimension(800,500));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
