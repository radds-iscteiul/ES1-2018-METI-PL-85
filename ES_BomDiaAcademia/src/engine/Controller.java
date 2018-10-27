package engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import config.XMLManager;
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

	/**
	 * @author Rafael Dias
	 * @param ArrayList<Service>
	 * 
	 * Starts the controller with the list of services obtained from the configuration file
	 */
	public void startMyController(ArrayList<Service> s) {
		servicos = new HashMap<ServiceType,Service>();
		this.readConfigurations(s);	
		mainWindow = new MainWindow();
		allMessages = new ArrayList<MyMessage>();
		allMessages.add(new EmailMessage("Rafael", "In�s","Aprsenta��o",new Date(), "Ol�, sou o Rafael, tudo bem?"));
		allMessages.add(new EmailMessage("Rafael","Marta","Declara��o", new Date(), "Two week in a virginia jail, for my lover, for my lover"));
	}
	/**
	 * @author Rafael Dias
	 * @param ArrayList<Service>
	 * Initializes from the ArrayList given by the XMLManager which provides the configuration file data.
	 */
	private void readConfigurations(ArrayList<Service> s) {
		for (Service service : s) {		
			servicos.put(service.getName(), service);	
		}
	}
	/**
	 * @author Rafael Dias
	 * @param ServiceType
	 * @return Given an hashMap <key> this method will return its value
	 */
	public Service getService(ServiceType service) {		
		return this.servicos.get(service);
	}
	
	/**
	 *@author Rafael Dias
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
	 * @author Rafael Dias
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
	 * @author Rafael Dias
	 * @return main window of the application
	 */
	public JFrame getMainWindow() {
		return mainWindow;
	}
	
	/**
	 * @author Rafael Dias
	 * @param Service
	 * 
	 * Toggle service's state (ative/ not ative)
	 */
	public void toogleServiceState(Service s) {
		s.toogleAtive();
	}
	
	public List<MyMessage> getAllMessages() {
		return this.allMessages;
	}
	/**
	 * @Rafael Dias
	 * @param List<MyMessage> 
	 * @return true if not problems were found
	 * 
	 * This method deletes all messages in the inbox and fullfills it with new messages
	 */
	public boolean addAllMessages(List<MyMessage> messages) {
		this.allMessages.clear();
		return this.allMessages.addAll(messages);		
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
	
	
	public void saveServicesToXML() {
		XMLManager xmlM = new XMLManager();
		try {
			xmlM.writeXML(this.getAllServices());
		} catch (TransformerException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
