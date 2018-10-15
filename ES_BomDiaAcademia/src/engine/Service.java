package engine;

public class Service {
	
	private int id;
	private ServiceType name;
	private String endereco;
	private String user;
	private String password;
	
	private boolean ative = true;
	
	public Service(int id, String n, String e, String u, String p) {
		this.id = id;
		this.name = this.identifyService(n);
		this.endereco = e;
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
	
	public String getUser() {
		return this.user;
	}
	public String getPassword() {
		return this.password;
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	public boolean isAtive() {
		return this.ative;
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
		return this.name+ " ; " + this.endereco;
	}

}
