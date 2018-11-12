package Teste;

import java.util.ArrayList;

import engine.Controller;
import engine.Service;
import junit.framework.TestCase;
import ui.MainWindow;
import ui.ServicePanel;

public class ServicePanelTest extends TestCase
{
	
	Service email = new Service(1,"Email", "metiG85.2018@gmail.com", "pw",true);
	Service facebook = new Service(2,"Facebook","Bárbara","pw",true);	
	ArrayList<Service> aux = new ArrayList<Service>();
	
	Controller controller = Controller.getInstance();
	ServicePanel sp;
	boolean ative;
	
	public void testAtiveToggle() {
		aux.add(email);
		aux.add(facebook);
		controller.startMyController(aux);
		sp = new ServicePanel((MainWindow)Controller.getInstance().getMainWindow());
		
		sp.lista.setSelectedIndex(0);
		assertEquals(false,sp.lista.isSelectionEmpty());
		assertEquals(0, sp.lista.getSelectedIndex());
		
		ative = sp.lista.getSelectedValue().isAtive();
		sp.toggle.doClick();
		assertEquals(!ative, sp.lista.getSelectedValue().isAtive());	
	}
	
}
