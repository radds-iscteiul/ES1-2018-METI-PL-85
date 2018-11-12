package Teste;

import java.util.ArrayList;

import javax.swing.JList;

import engine.Controller;
import engine.FacebookService;
import engine.Service;
import engine.TwitterService;
import junit.framework.TestCase;
import ui.MainWindow;
import ui.ServicePanel;

public class UITests extends TestCase
{
	
	Controller controller;
	MainWindow mainWindow;
	
	public void startComponents() {
		ArrayList<Service> servicesList = new ArrayList<Service>();
		
		servicesList.add(new Service(1,"Email", "metiG85.2018@gmail.com", "pw",true));
		servicesList.add(new FacebookService(2,"Facebook","Barbara","pw",true,"facebookTOKEN"));
		servicesList.add(new TwitterService(3, "Twitter", "rafael", "rafaelpw", false, "ISCTE", "TwitterKEY", "TwitterSECRET", "TwitterTOKEN", "TwitterTOKENSECRET"));
		
		controller = Controller.getInstance();
		controller.startMyController(servicesList);
		mainWindow = new MainWindow();
	}
	
	public void testServicePanel() {
		ServicePanel servicePanel = (ServicePanel)mainWindow.getServicePanel();
		JList<Service> serviceList = servicePanel.getServiceList();
		
		assertEquals(3,serviceList.getSize());
		servicePanel.getServiceList().setSelectedIndex(0);
		
		
		Service firstService = servicePanel.getServiceList().getSelectedValue();
		
		assertEquals(firstService != null, firstService);
		boolean firsServiceStatus = firstService.isAtive();
		servicePanel.getToggleButton().doClick();
		
		assertEquals(!firsServiceStatus, firstService.isAtive());
	}

}
