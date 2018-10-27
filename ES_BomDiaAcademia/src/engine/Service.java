package engine;

/**
 * 
 * @author Rafael Dias
 *
 */
public class Service {
	
	private int id;
	private ServiceType name;
	private String user;
	private String password;
	
	private boolean ative = true;
	
	public Service(int id, String n, String u, String p) {
		this.id = id;
		this.name = this.identifyService(n);
		this.user = u;
		this.password = p;
	}
	public boolean toogleAtive() {
		ative = !ative;
		return ative;
	}
	public ServiceType getName() {
		return this.name;
	}
	public int getId() {
		return this.id;
	}	
	public String getUser() {
		return this.user;
	}
	public String getPassword() {
		return this.password;
	}
	public boolean isAtive() {
		return this.ative;
	}
	
	public void setUser(String u) {
		this.user = u;
	}
	public void setPassword(String p) {
		this.password = p;
	}
	
	public ServiceType identifyService(String service) {
		if (service.equals("Email")) {
			return ServiceType.EMAIL;
		} else if (service.equals("Facebook")){
			return ServiceType.FACEBOOK;
		} else if (service.equals("Twitter")) {
			return ServiceType.TWITTER;
		} else {
			return null;
		}
	}
	@Override
	public String toString() {
		return this.name.toString();
	}

}
