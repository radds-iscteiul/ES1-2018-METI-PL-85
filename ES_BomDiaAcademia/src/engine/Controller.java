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
	private List<EmailMessage> allMessages;
	
	private Controller() {
	}
	
	public static Controller getInstance( ) {
	      if (INSTANCE == null) {
	    	  INSTANCE = new Controller();
	      }
	      return INSTANCE;
	}

	/**
	 * 
	 * @param ArrayList<Service>
	 * 
	 * Starts the controller with the list of services obtained from the configuration file
	 */
	public void startMyController(ArrayList<Service> s) {
		servicos = new HashMap<ServiceType,Service>();
		this.readConfigurations(s);	
		mainWindow = new MainWindow();
		allMessages = new ArrayList<EmailMessage>();
	}
	/**
	 * 
	 * @param ArrayList<Service>
	 * Initializes from the ArrayList given by the XMLManager which provides the configuration file data.
	 */
	private void readConfigurations(ArrayList<Service> s) {
		for (Service service : s) {		
			servicos.put(service.getName(), service);	
		}
	}
	/**
	 * 
	 * @param ServiceType
	 * @return Given an hashMap <key> this method will return its value
	 */
	public Service getService(ServiceType service) {		
		return this.servicos.get(service);
	}
	
	/**
	 *
	 * @return All existing services from the configuration
	 */
	public ArrayList<Service> getAllServices() {
		ArrayList<Service> s = new ArrayList<Service>();
		
		for(Service service : this.servicos.values()) {
			s.add(service);
		}
		return s;
	}
	/**
	 * 
	 * @return All currently ative services
	 */
	public List<Service> getAtiveServices(){
		List<Service> s = new ArrayList<Service>();
		for(Service service : this.servicos.values()) {
			if(service.isAtive()) {
				s.add(service);
			}
		}
		return s;
	}
	
	/**
	 * 
	 * @return main window of the application
	 */
	public JFrame getMainWindow() {
		return mainWindow;
	}
	
	/**
	 * 
	 * @param Service
	 * 
	 * Toggle service's state (ative/ not ative)
	 */
	public void toogleServiceState(Service s) {
		s.toogleAtive();
	}
	
	
	/**
	 * @author Ines Duarte
	 * @param MyMessage
	 * @param String
	 * @return True if the given word is within the given message
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
