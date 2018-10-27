package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainWindow extends JFrame{

	private JPanel servicePanel;
	private JPanel informationPanel;
	
	public MainWindow() {
		super("BDA - Bom dia academia");
		this.setLayout(new BorderLayout());
		
		servicePanel = new ServicePanel(this);
		informationPanel = new InformationPanel(this);
		
		this.add(servicePanel,BorderLayout.EAST);
		this.add(informationPanel,BorderLayout.CENTER);
		this.start();
	}
	
	private void start() {
		this.setSize(new Dimension(800,500));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void updateMessageListUI() {
		InformationPanel ip = (InformationPanel)informationPanel;
		ip.updateMessageUI();
	}
	
}
