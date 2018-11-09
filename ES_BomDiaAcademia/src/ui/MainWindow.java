package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainWindow extends JFrame{

	private JPanel servicePanel;
	private JPanel informationPanel;
	
	public MainWindow() {
		super("BDA - Bom dia academia");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		
		servicePanel = new ServicePanel(this);
		informationPanel = new InformationPanel(this);
		
		this.add(servicePanel,BorderLayout.EAST);
		this.add(informationPanel,BorderLayout.CENTER);
		this.start();
	}
	
	private void start() {
		double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double screenHeight= Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		this.setSize(new Dimension((int)screenWidth - 300,(int)screenHeight - 300));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void updateMessageListUI() {
		InformationPanel ip = (InformationPanel)informationPanel;
		ip.updateMessageUI();
	}
	
}
