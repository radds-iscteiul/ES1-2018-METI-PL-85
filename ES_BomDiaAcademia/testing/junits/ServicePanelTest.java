package junits;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.swing.plaf.ColorUIResource;

import org.junit.Test;

import com.sun.prism.paint.Color;

import engine.Controller;
import engine.Service;
import ui.MainWindow;
import ui.ServicePanel;

public class ServicePanelTest {
	
	Service email = new Service(1,"Email", "metiG85.2018@gmail.com", "pw");
	Service facebook = new Service(2,"Facebook","Bárbara","pw");	
	ArrayList<Service> aux = new ArrayList<Service>();
	
	Controller controller = Controller.getInstance();
	ServicePanel sp;
	boolean ative;
	
	@Test
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
