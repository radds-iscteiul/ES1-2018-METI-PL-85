package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import ui.MainWindow;

/**
 * 
 * @author Rafael Dias
 * 
 * This class controller will be responsible for managing every logical procedure needed
 *
 *	Uses Singleton pattern
 */
public class Controller{
	
	private static Controller INSTANCE = null;
	
	private JFrame mainWindow;
	private Map<ServiceType,Service> servicos;
	private List<MyMessage> allMessages;
	
	private Controller() {
	}
	
	public static Controller getInstance( ) {
	      if (INSTANCE == null) {
	    	  INSTANCE = new Controller();
	      }
	      return INSTANCE;
	}

	public void startMyController(ArrayList<Service> s) {
		servicos = new HashMap<ServiceType,Service>();
		this.readConfigurations(s);	
		mainWindow = new MainWindow();
		allMessages = new ArrayList<MyMessage>();
	}
	/**
	 * @author Rafael Dias
	 * @param s
	 * Initializes from the ArrayList given by the XMLManager which provides the configuration file data.
	 */
	private void readConfigurations(ArrayList<Service> s) {
		for (Service service : s) {		
			servicos.put(service.getName(), service);	
		}
	}
	/**
	 * @author Rafael Dias
	 * @param service
	 * @return Given an hashMap <key> this method will return its value
	 */
	public Service getService(ServiceType service) {		
		return this.servicos.get(service);
	}
	
	/**
	 * 
	 * @return All service available from the configuration
	 */
	public ArrayList<Service> getAllServices() {
		ArrayList<Service> s = new ArrayList<Service>();
		
		for(Service service : this.servicos.values()) {
			s.add(service);
		}
		return s;
	}
	public List<Service> getAtiveServices(){
		List<Service> s = new ArrayList<Service>();
		for(Service service : this.servicos.values()) {
			if(service.isAtive()) {
				s.add(service);
			}
		}
		return s;
	}
	
	public JFrame getMainWindow() {
		return mainWindow;
	}
	
	public void toogleServiceState(Service s) {
		s.toogleAtive();
	}
	
	
	/**
	 * @author Inês Duarte
	 * @param message
	 * @param palavra
	 * @return True if you find the word you want within a message
	 */
	
	public boolean wordFilter (MyMessage message, String palavra){
		String m = message.getMessage();
		
		for(int i = 0; ( i < m.length()-palavra.length()); i++) {
			String aux = m.substring(i, i+palavra.length());
			if( palavra.equals(aux)) {
				System.out.println("Found");
				System.out.println("Palavra encontrada na mensagem : " + palavra );	
				return true;
			}	
		}
		return false;
	}
	
	

}
